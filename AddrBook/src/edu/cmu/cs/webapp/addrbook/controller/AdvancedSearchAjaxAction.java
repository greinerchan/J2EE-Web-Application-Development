package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.addrbook.databean.Entry;
import edu.cmu.cs.webapp.addrbook.formbean.AdvancedSearchForm;
import edu.cmu.cs.webapp.addrbook.model.EntryDAO;
import edu.cmu.cs.webapp.addrbook.model.Model;

public class AdvancedSearchAjaxAction extends Action {
    private EntryDAO entryDAO;

    public AdvancedSearchAjaxAction(Model model) {
        entryDAO = model.getEntryDAO();
    }

    public String getName() {
        return "advanced-search-ajax.do";
    }
    
    // Provide a performGet to that during class we can
    // more easily demo what is returned
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }

    // In this method we receive the ajax request from the javascript in
    // advanced-search-form.jsp
    // We lookup in the DB the query results and we return to
    // advanced-search-ajax.jsp
    // so it displays the results in XML format.
    public String performPost(HttpServletRequest request) {
        try {
            AdvancedSearchForm form = new AdvancedSearchForm(request);

            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                return "form-error.jsp";
            }

            Entry[] entryList = entryDAO.lookupAdvanced(form);
            request.setAttribute("entryList", entryList);
            return "advanced-search-ajax.jsp";
        } catch (RollbackException e) {
            e.printStackTrace();
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
