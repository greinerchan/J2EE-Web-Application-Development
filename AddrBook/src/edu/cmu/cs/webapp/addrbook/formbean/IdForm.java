package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;

public class IdForm extends FormBean {
    private String id;

    public IdForm() {
        super();
    }

    public IdForm(HttpServletRequest request) {
        super(request);
    }

    public String getId() {
        return id;
    }

    public int getIdNum() {
        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
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
