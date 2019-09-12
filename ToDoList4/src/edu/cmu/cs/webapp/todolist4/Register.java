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

import edu.cmu.cs.webapp.todolist4.dao.UserDAO;
import edu.cmu.cs.webapp.todolist4.databean.User;
import edu.cmu.cs.webapp.todolist4.formbean.RegisterForm;

@WebServlet("/Register")
public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            ConnectionPool cp = new ConnectionPool(jdbcDriverName, jdbcURL);
    
            cp.setDebugOutput(System.out);  // Print out the generated SQL

            userDAO = new UserDAO(cp, "user");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect("ToDoList");
            return;
        }
        
        RequestDispatcher d = request.getRequestDispatcher("register.jsp");
        d.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect("ToDoList");
            return;
        }

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                RequestDispatcher d = request.getRequestDispatcher("register.jsp");
                d.forward(request, response);
                return;
            }

            User user = new User();
            user.setUserName(form.getUserName());
            user.setPassword(form.getPassword());
            userDAO.create(user);

            session.setAttribute("user", user);
            response.sendRedirect("ToDoList");

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
}