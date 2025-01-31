package edu.cmu.cs.webapp.todolist5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist5.model.ItemDAO;
import edu.cmu.cs.webapp.todolist5.model.Model;

public class ToDoListAction extends Action {
    private ItemDAO itemDAO;

    public ToDoListAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "todolist.do";
    }
    
    public String performGet(HttpServletRequest request) {
        return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            request.setAttribute("items", itemDAO.getItems());
            return ("todolist.jsp");
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
