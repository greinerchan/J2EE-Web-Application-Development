package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.Entry;
import edu.cmu.cs.webapp.addrbook.formbean.IdButtonForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class ModifyEntryAction extends Action {
    private EntryDAO entryDAO;

    public ModifyEntryAction(Model model) {
        entryDAO = model.getEntryDAO();
    }

    public String getName() {
        return "modify-entry.do";
    }

    public String performPost(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            IdButtonForm form = new IdButtonForm(request);
            if (form.hasValidationErrors()) {
                return "search.jsp";
            }

            int id = form.getIdNum();
            Entry entry = entryDAO.read(id);
            if (entry == null) {
                form.addFormError("No record with id=" + id);
                return "search.jsp";
            }

            if (form.getButton().equals("Delete")) {
                request.setAttribute("entry", entry);
                return "delete-entry.jsp";
            }

            if (form.getButton().equals("Edit")) {
                request.setAttribute("entry", entry);
                request.setAttribute("digest", entryDAO.computeDigest(entry));
                return "edit-entry.jsp";
            }

            form.addFormError("Unknown Button: " + form.getButton());
            return "search.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
