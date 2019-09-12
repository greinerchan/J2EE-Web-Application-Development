package edu.cmu.cs.webapp.todolist4.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ItemForm {
    private String action;
    private String item;

    public ItemForm(HttpServletRequest request) {
        action = request.getParameter("action");
        item = request.getParameter("item");
    }

    public String getAction() {
        return action;
    }
    
    public String getItem() {
        return item;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (item == null || item.length() == 0) {
            errors.add("Item is required");
        }
        
        if (action == null) {
            errors.add("Action is required");
        } else if (!action.equals("top") && !action.equals("bottom")) {
            errors.add("Invalid action: " + action);
        }

        return errors;
    }
}
