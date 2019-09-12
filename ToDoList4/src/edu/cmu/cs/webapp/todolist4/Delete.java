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
import edu.cmu.cs.webapp.todolist4.databean.User;
import edu.cmu.cs.webapp.todolist4.formbean.IdForm;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

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
            IdForm form = new IdForm(request);
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                RequestDispatcher d = request.getRequestDispatcher("error.jsp");
                d.forward(request, response);
                return;
            }

            itemDAO.delete(form.getIdAsInt());

            response.sendRedirect("ToDoList");
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
}