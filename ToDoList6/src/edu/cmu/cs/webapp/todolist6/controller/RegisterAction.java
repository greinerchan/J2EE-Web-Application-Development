package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.databean.User;
import edu.cmu.cs.webapp.todolist6.formbean.RegisterForm;
import edu.cmu.cs.webapp.todolist6.model.Model;
import edu.cmu.cs.webapp.todolist6.model.UserDAO;

public class RegisterAction extends Action {
    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "register.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        if (request.getSession().getAttribute("user") != null) {
            return "todolist.do";
        }

        // Otherwise, just display the register page.
        request.setAttribute("form", new RegisterForm());
        return "register.jsp";
    }

    public String performPost(HttpServletRequest request) {
        // If user is ALREADY logged in, redirect to todolist.do
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "todolist.do";
        }

    	RegisterForm form = new RegisterForm(request);
        request.setAttribute("form", form);

        // Any validation errors?
        if (form.hasValidationErrors()) {
            return "register.jsp";
        }

        try {
            User newUser = new User();
            newUser.setUserName(form.getUserName());
            newUser.setPassword(form.getPassword());
            userDAO.create(newUser);

            session.setAttribute("user", newUser);
            return ("todolist.do");
        } catch (DuplicateKeyException e) {
            form.addFieldError("userName", "A user with this name already exists");
            return "login.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
