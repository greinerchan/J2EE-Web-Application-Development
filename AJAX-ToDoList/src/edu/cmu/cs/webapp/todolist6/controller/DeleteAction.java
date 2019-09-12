package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.formbean.IdForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class DeleteAction extends Action {
    private ItemDAO itemDAO;

    public DeleteAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "delete.do";
    }

    public String performPost(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("error", "Please log in");
            return "json-error.jsp";
        }

        try {
            IdForm form = new IdForm(request);
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                return "json-error.jsp";
            }

            itemDAO.delete(form.getIdAsInt());

            request.setAttribute("items", itemDAO.getItems());
            return "json-list.jsp";

        } catch (RollbackException e) {
        	e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            return "json-error.jsp";
        }
    }
}
