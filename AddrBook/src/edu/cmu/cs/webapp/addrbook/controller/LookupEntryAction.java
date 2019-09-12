package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.Entry;
import edu.cmu.cs.webapp.addrbook.formbean.IdForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.LogDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class LookupEntryAction extends Action {
    private EntryDAO entryDAO;
    private LogDAO logDAO;

    public LookupEntryAction(Model model) {
        entryDAO = model.getEntryDAO();
        logDAO = model.getLogDAO();
    }

    public String getName() {
        return "lookup-entry.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            IdForm form = new IdForm(request);
            request.setAttribute("form", form);
            if (form.hasValidationErrors()) {
                return "search.jsp";
            }

            int id = form.getIdNum();
            Entry entry = entryDAO.read(id);
            if (entry == null) {
                logDAO.write(request, "lookupAction: id=" + id + ", entry=null");
                form.addFormError("No record with id=" + id);
                return "search.jsp";
            }

            logDAO.write(request, "lookupAction: id=" + id + ", lastName=" + entry.getLastName() + ", firstName=" + entry.getFirstNames());
            request.setAttribute("entry", entry);
            return "display-entry.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
