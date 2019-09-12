package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.formbeanfactory.FormBeanFactoryException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.databean.ItemBean;
import edu.cmu.cs.webapp.todolist6.databean.User;
import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class AddAction extends Action {
    private ItemDAO itemDAO;

    public AddAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "add.do";
    }

    public String performPost(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("error", "Please log in");
            return "json-error.";
        }

        try {
            ItemForm form = new ItemForm(request);
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                return "json-error.jsp";
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

            request.setAttribute("items", itemDAO.getItems());

            return "json-list.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "json-error.jsp";
        }
    }
}
