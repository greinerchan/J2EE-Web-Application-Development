package edu.cmu.cs.webapp.todolist5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist5.databean.ItemBean;
import edu.cmu.cs.webapp.todolist5.databean.User;
import edu.cmu.cs.webapp.todolist5.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist5.model.ItemDAO;
import edu.cmu.cs.webapp.todolist5.model.Model;

public class AddAction extends Action {
    private ItemDAO itemDAO;

    public AddAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "add.do";
    }

    public String performPost(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            // Fetch the items now, so that in case there is no form or there are errors
            // We can just dispatch to the JSP to show the item list (and any errors)
            request.setAttribute("items", itemDAO.getItems());

            ItemForm form = new ItemForm(request);
            request.setAttribute("form", form);

            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "todolist.jsp";
            }

            ItemBean bean = new ItemBean();
            bean.setItem(form.getItem());
            bean.setIpAddress(request.getRemoteAddr());
            bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());

            if (form.getAction().equals("top")) {
                itemDAO.addToTop(bean);
            } else {
                itemDAO.addToBottom(bean);
            }

            // Fetch the items again, since we modified the list
            request.setAttribute("items", itemDAO.getItems());

            return "todolist.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
