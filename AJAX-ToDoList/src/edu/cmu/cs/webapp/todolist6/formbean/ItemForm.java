package edu.cmu.cs.webapp.todolist6.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

public class ItemForm extends FormBean {
    private String action;
    private String item;
    
    public ItemForm() {
    	super();
    }
    
    public ItemForm(HttpServletRequest request) {
    	super(request);
    }
  
    public String getAction() {
        return action;
    }
    
    public String getItem() {
        return item;
    }
    
    @InputType("button")
    public void setAction(String action) {
        this.action = action;
    }

    @Label("Item to add:")
    public void setItem(String item) {
        this.item = item;
    }

    public void validate() {
        super.validate();
        
        if (hasValidationErrors()) {
            return;
        }
        
        if (!action.equals("top") && !action.equals("bottom")) {
            addFormError("Invalid action: " + action);
        }
    }
}
