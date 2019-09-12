package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;

public class IdButtonForm extends FormBean {
    private String id;
    private String button;

    public IdButtonForm() {
        super();
    }

    public IdButtonForm(HttpServletRequest request) {
        super(request);
    }

    public String getId() {
        return id;
    }

    public String getButton() {
        return button;
    }

    public int getIdNum() {
        return Integer.parseInt(id);
    }

    public void setId(String s) {
        id = s;
    }

    public void setButton(String s) {
        button = s;
    }

    public void validate() {
        super.validate();

        if (hasValidationErrors()) {
            return;
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            addFormError("Id is not an integer");
        }
    }
}
