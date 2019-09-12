package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.databean.User;
import edu.cmu.cs.webapp.todolist6.formbean.LoginForm;
import edu.cmu.cs.webapp.todolist6.model.Model;
import edu.cmu.cs.webapp.todolist6.model.UserDAO;

public class LoginAction extends Action {
    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        if (request.getSession().getAttribute("user") != null) {
            return "todolist.do";
        }

        // Otherwise, just display the login page.
        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "todolist.do";
        }

        try {
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "login.jsp";
            }

            // Look up the user
            User user = userDAO.read(form.getUserName());
            if (user == null) {
                form.addFieldError("userName", "User Name not found");
                return "login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "todolist.do";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
