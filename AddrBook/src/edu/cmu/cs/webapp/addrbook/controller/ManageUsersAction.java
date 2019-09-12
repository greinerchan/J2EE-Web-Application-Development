package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.formbean.DeleteUserForm;
import edu.cmu.cs.webapp.addrbook.formbean.ManageUserForm;
import edu.cmu.cs.webapp.addrbook.formbean.SetPasswordForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;
import edu.cmu.cs.webapp.addrbook.model.UserDAO;

public class ManageUsersAction extends Action {
    private EntryDAO entryDAO;
    private UserDAO userDAO;

    public ManageUsersAction(Model model) {
        entryDAO = model.getEntryDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "manage-users.do";
    }
    
    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());
            request.setAttribute("userList", userDAO.match());
            return "manage-users.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }

    public String performPost(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());
            request.setAttribute("userList", userDAO.match());
            
            ManageUserForm form = new ManageUserForm(request);
            request.setAttribute("form", form);
            if (form.hasValidationErrors()) {
                return "manage-users.jsp";
            }
            
            if (form.getButton().equals("Delete")) {
                DeleteUserForm deleteForm = new DeleteUserForm();
                deleteForm.setUserName(form.getUserName());
                request.setAttribute("form", deleteForm);
                return "delete-user.jsp";
            }

            if (form.getButton().equals("Set Password")) {
                SetPasswordForm pwdForm = new SetPasswordForm();
                pwdForm.setUserName(form.getUserName());
                request.setAttribute("form", pwdForm);
                return "set-password.jsp";
            }

            form.addFormError("Unknown button: " + form.getButton());
            return "manage-users.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
