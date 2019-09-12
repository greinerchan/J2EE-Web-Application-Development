package xc4.hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import xc4.hw5.databean.User;
import xc4.hw5.formbean.LoginForm;
import xc4.hw5.model.Model;
import xc4.hw5.model.UserDAO;

public class LoginAction extends Action {
    private UserDAO userDAO; 
    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }
    public String performGet(HttpServletRequest request) {        
        // Otherwise, just display the login page.
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
		try {
			User[] users;
			users = userDAO.getUsers();
			request.setAttribute("users", users);
	        return "login.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    } 

    public String performPost(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        
        try {
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());
            if (!form.isPresent()) {
            	errors.add("Button is required");
                return "login.jsp";
            }
            if (form.notExist()) {
            	errors.add("Button is not Existed");
                return "login.jsp";
            }

            if (form.getButton().equals("Login")) {
                return "login.jsp";
            }
            if (form.getButton().equals("Register")) {
                return "register.jsp";
            }
            
            if (form.redirect()) {
            	return "login.jsp";
            }
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "login.jsp";
            }
            // Look up the user
            User user = userDAO.read(form.getEmail());

            if (user == null) {
                errors.add("User Email not Found");
                return "login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                errors.add("Incorrect Password");
                return "login.jsp";
            }
            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "home.do";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "home.jsp";
        }
    }
}
