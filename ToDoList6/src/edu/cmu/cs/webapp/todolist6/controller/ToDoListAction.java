package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class ToDoListAction extends Action {
    private ItemDAO itemDAO;

    public ToDoListAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "todolist.do";
    }

    public String performGet(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }

        try {
            request.setAttribute("items", itemDAO.getItems());
            request.setAttribute("form",  new ItemForm());
            return ("todolist.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
