package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class FetchListAction extends Action {
    private ItemDAO itemDAO;

    public FetchListAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "fetch-list.do";
    }
    
    public String performGet(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("error", "Please log in");
            return "json-error.jsp";
        }

        try {
            request.setAttribute("items", itemDAO.getItems());
            return ("json-list.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "json-error.jsp";
        }
    }
}
