package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.formbean.EntryForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class AddEntryAction extends Action {
    private EntryDAO entryDAO;

    public AddEntryAction(Model model) {
        entryDAO = model.getEntryDAO();
    }

    public String getName() {
        return "add-entry.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            EntryForm form = new EntryForm();
            form.setId("0");
            request.setAttribute("form", form);
            return "edit-form.jsp";
        } catch (RollbackException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
