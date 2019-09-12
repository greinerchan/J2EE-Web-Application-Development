package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.User;
import edu.cmu.cs.webapp.addrbook.formbean.ManageUserForm;
import edu.cmu.cs.webapp.addrbook.formbean.SetPasswordForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;
import edu.cmu.cs.webapp.addrbook.model.UserDAO;

public class SetPasswordAction extends Action {
    private EntryDAO entryDAO;
    private UserDAO userDAO;

    public SetPasswordAction(Model model) {
        entryDAO = model.getEntryDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "set-password.do";
    }

    public String performPost(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            SetPasswordForm form = new SetPasswordForm(request);
            request.setAttribute("form", form);

            if (form.hasValidationErrors()) {
                return "set-password.jsp";
            }

            userDAO.setPassword(form.getUserName(), form.getNewPassword());

            ManageUserForm manageForm = new ManageUserForm();
            manageForm.addFormError("Password changed for user " + form.getUserName());
            request.setAttribute("form", manageForm);
            User[] userList = userDAO.match();
            request.setAttribute("userList", userList);
            return "manage-users.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
