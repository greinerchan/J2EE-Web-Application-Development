package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.User;
import edu.cmu.cs.webapp.addrbook.formbean.DeleteUserForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;
import edu.cmu.cs.webapp.addrbook.model.UserDAO;

public class DeleteUserAction extends Action {
    private EntryDAO entryDAO;
    private UserDAO userDAO;

    public DeleteUserAction(Model model) {
        entryDAO = model.getEntryDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "delete-user.do";
    }

    public String performPost(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            User[] userList = userDAO.match();
            request.setAttribute("userList", userList);

            DeleteUserForm form = new DeleteUserForm(request);
            request.setAttribute("form", form);
            if (form.hasValidationErrors()) {
                return "manage-users.jsp";
            }
            
            if (!form.getButton().equals("Yes")) {
                return "manage-users.jsp";
            }

            User user = (User) request.getSession(true).getAttribute("user");
            if (user.getUserName().equals(form.getUserName())) {
                form.addFormError("Cannot delete yourself");
                return "manage-users.jsp";
            }
            
            userDAO.delete(form.getUserName());

            // Refresh userList because we've changed it
            userList = userDAO.match();
            request.setAttribute("userList", userList);

            return "manage-users.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
