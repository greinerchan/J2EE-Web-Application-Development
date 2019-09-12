package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.User;
import edu.cmu.cs.webapp.addrbook.formbean.LoginForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;
import edu.cmu.cs.webapp.addrbook.model.UserDAO;

public class LoginAction extends Action {
    private EntryDAO entryDAO;
    private UserDAO userDAO;

    public LoginAction(Model model) {
        entryDAO = model.getEntryDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());
            
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                // Already logged in...
                return "search.do";
            }

            LoginForm form = new LoginForm();
            request.setAttribute("form", form);
            return "login.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
    
    public String performPost(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());
            
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                // Already logged in...
                return "search.do";
            }
    
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);

            if (form.hasValidationErrors()) {
                return "login.jsp";
            }
            
            user = userDAO.read(form.getUserName());
            if (user == null) {
                form.addFieldError("userName", "Username invalid");
                return "login.jsp";
            }

            if (!user.checkPassword(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "login.jsp";
            }

            request.getSession().setAttribute("user", user);

            if (form.getRedirect().length() > 0) {
                return form.getRedirect();
            }

            return "search.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
