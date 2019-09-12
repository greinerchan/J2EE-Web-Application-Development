package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.Entry;
import edu.cmu.cs.webapp.addrbook.formbean.SearchForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.LogDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class SearchAction extends Action {
    private EntryDAO entryDAO;
    private LogDAO logDAO;

    public SearchAction(Model model) {
        entryDAO = model.getEntryDAO();
        logDAO = model.getLogDAO();
    }

    public String getName() {
        return "search.do";
    }

    public String performGet(HttpServletRequest request) {
        try {
            request.setAttribute("numEntries", entryDAO.getCount());
            
            SearchForm searchForm = new SearchForm(request);
            request.setAttribute("form", searchForm);

            if (searchForm.hasValidationErrors()) {
                return "search.jsp";
            }
            
            if (searchForm.getLast().length() == 0 && searchForm.getLast().length() == 0) {
                return "search.jsp";
            }

            Entry[] list = entryDAO.lookupStartsWith(searchForm.getLast(), searchForm.getFirst());
            Entry[] spouseList = entryDAO.lookupSpouseStartsWith(searchForm.getLast(), searchForm.getFirst());

            logDAO.write(request, "searchAction: last=" + searchForm.getLast() + ", first=" + searchForm.getFirst()
                    + ", list.length=" + list.length + ", spouseList.length=" + spouseList.length);

            if (list.length + spouseList.length == 0) {
                searchForm.addFormError("No matches for last name starts with \"" + searchForm.getLast()
                        + "\" and first starts with \"" + searchForm.getFirst() + "\"");
                return "search.jsp";
            }

            if (list.length + spouseList.length > 1) {
                request.setAttribute("list", list);
                request.setAttribute("spouseList", spouseList);
                return "list-entries.jsp";
            }

            if (list.length == 1) {
                request.setAttribute("entry", list[0]);
                return "display-entry.jsp";
            }

            request.setAttribute("entry", spouseList[0]);
            return "display-entry.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
