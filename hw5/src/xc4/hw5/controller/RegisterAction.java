
package xc4.hw5.controller;

import xc4.hw5.databean.User;
import xc4.hw5.formbean.RegisterForm;
import xc4.hw5.model.Model;
import xc4.hw5.model.UserDAO;

import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class RegisterAction extends Action {

    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }
    public String getName() {
        return "register.do";
    }
    public String performGet(HttpServletRequest request) {        
        // Otherwise, just display the login page.
    	return performPost(request);
    } 
    public String performPost(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        HttpSession session = request.getSession();
        try {
            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());
            if (!form.isPresent()) {
            	errors.add("Button is required");
                return "register.jsp";
            }
            if (form.notExist()) {
            	errors.add("Button is not Existed");
                return "register.jsp";
            }
            
            if (form.getButton().equals("Login")) {
                return "login.jsp";
            }
            if (form.getButton().equals("Register")) {
                return "register.jsp";
            }
            
            if (userDAO.read(form.getEmail()) != null) {
            	errors.add("The email is Existed");
            }

            errors.addAll(form.getValidationErrors());
            
            if (errors.size() != 0) {
                return "register.jsp";
            }

            User user = new User();

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(form.getPassword());
            userDAO.create(user);
            User[] users = userDAO.getUsers();
            request.setAttribute("users", users);
            session.setAttribute("user", user);

            return "home.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "register.jsp";
        } catch (Exception e) {
            return "register.jsp";
        } 
    }
}
