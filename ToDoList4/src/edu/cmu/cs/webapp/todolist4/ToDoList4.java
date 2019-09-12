package edu.cmu.cs.webapp.todolist4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist4.dao.ItemDAO;
import edu.cmu.cs.webapp.todolist4.databean.ItemBean;
import edu.cmu.cs.webapp.todolist4.databean.User;
import edu.cmu.cs.webapp.todolist4.formbean.ItemForm;

@WebServlet("/ToDoList")
public class ToDoList4 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ItemDAO itemDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            ConnectionPool cp = new ConnectionPool(jdbcDriverName, jdbcURL);
    
            cp.setDebugOutput(System.out);  // Print out the generated SQL

            itemDAO = new ItemDAO(cp, "todolist");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Login");
            return;
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
        	request.setAttribute("items", itemDAO.getItems());
            RequestDispatcher d = request.getRequestDispatcher("todolist.jsp");
            d.forward(request, response);
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        } 
    }
    
    // We need to support GET and POST requests because
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Login");
            return;
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            // Fetch the items now, so that in case this is a GET Request or if there
            // are errors, we can just dispatch to the JSP to show the item list
            // (and any errors)
            request.setAttribute("items", itemDAO.getItems());

            ItemForm form = new ItemForm(request);
            request.setAttribute("form", form);
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                RequestDispatcher d = request.getRequestDispatcher("todolist.jsp");
                d.forward(request, response);
                return;
            }

            ItemBean bean = new ItemBean();
            bean.setItem(form.getItem());
            bean.setIpAddress(request.getRemoteAddr());
            bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());

            if (form.getAction().equals("top")) {
                itemDAO.addToTop(bean);
            } else {
                itemDAO.addToBottom(bean);
            }

            // Fetch the items again, since we modified the list
            request.setAttribute("items", itemDAO.getItems());

            RequestDispatcher d = request.getRequestDispatcher("todolist.jsp");
            d.forward(request, response);
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
}