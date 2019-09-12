package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.User;
import edu.cmu.cs.webapp.addrbook.formbean.CreateUserForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;
import edu.cmu.cs.webapp.addrbook.model.UserDAO;

public class CreateUserAction extends Action {
    private EntryDAO entryDAO;
    private UserDAO userDAO;

    public CreateUserAction(Model model) {
        entryDAO = model.getEntryDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "create-user.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            request.setAttribute("form", new CreateUserForm());
            return "create-user.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error", e.toString());
            return "error.jsp";
        }
    }
    
    public String performPost(HttpServletRequest request) {
        try {
            int numEntries = entryDAO.getCount();
            request.setAttribute("numEntries", numEntries);
            
            CreateUserForm form = new CreateUserForm(request);
            request.setAttribute("form", form);
            if (form.hasValidationErrors()) {
                return "create-user.jsp";
            }
            
            try {
                User newUser = new User();
                newUser.setUserName(form.getUserName());
                newUser.encodePassword(form.getPassword());
                userDAO.create(newUser);

                User user = (User) request.getSession().getAttribute("user");
                if (user == null) {
                    // This is the initial user...log in (by setting session attribute)
                    request.getSession().setAttribute("user", newUser);
                }
            } catch (DuplicateKeyException e) {
                form.addFieldError("userName", "User already exists.");
                return "create-user.jsp";
            }

            User[] userList = userDAO.match();
            request.setAttribute("userList", userList);
            return "manage-users.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
