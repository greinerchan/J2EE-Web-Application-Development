package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.formbean.IdForm;
import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
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
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }

        try {
            IdForm form = new IdForm(request);

            if (form.hasValidationErrors()) {
            	request.setAttribute("form", form);
                return "error.jsp";
            }

            itemDAO.delete(form.getIdAsInt());

            request.setAttribute("items", itemDAO.getItems());
            request.setAttribute("form", new ItemForm());
            return "todolist.jsp";
        } catch (RollbackException e) {
            request.setAttribute("error", e.getMessage());
            return "error.jsp";
        }
    }
}
