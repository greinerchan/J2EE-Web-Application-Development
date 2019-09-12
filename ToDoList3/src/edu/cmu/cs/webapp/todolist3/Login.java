package edu.cmu.cs.webapp.todolist3;

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

import edu.cmu.cs.webapp.todolist3.dao.MyDAOException;
import edu.cmu.cs.webapp.todolist3.dao.UserDAO;
import edu.cmu.cs.webapp.todolist3.databean.User;
import edu.cmu.cs.webapp.todolist3.formbean.LoginForm;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            userDAO = new UserDAO(jdbcDriverName, jdbcURL, "user");
        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect("ToDoList");
            return;
        }
        
        RequestDispatcher d = request.getRequestDispatcher("login.jsp");
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
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                d.forward(request, response);
                return;
            }

            User user = userDAO.read(form.getUserName());
            if (user == null) {
                errors.add("No such user");
                RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                d.forward(request, response);
                return;
            }

            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password");
                RequestDispatcher d = request.getRequestDispatcher("login.jsp");
                d.forward(request, response);
                return;
            }

            session.setAttribute("user", user);
            response.sendRedirect("ToDoList");

        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("error.jsp");
            d.forward(request, response);
        }
    }
}