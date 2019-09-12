package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.Entry;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class DumpDataAction extends Action {
    private EntryDAO entryDAO;

    public DumpDataAction(Model model) {
        entryDAO = model.getEntryDAO();
    }

    public String getName() {
        return "dump-data.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());

            Entry[] list = entryDAO.match();
            request.setAttribute("list", list);
            return "dump-entries.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
