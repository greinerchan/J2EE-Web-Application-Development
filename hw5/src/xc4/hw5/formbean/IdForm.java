package xc4.hw5.formbean;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

public class IdForm extends FormBean {
    private String id;
    
    public IdForm() {
    	super();
    }
    
    public IdForm(HttpServletRequest request) {
    	id = ssanitize(request.getParameter("id"));
    }

    public String getId() { return id;    }
    public int getIdAsInt() {
        // The call validate() to ensures that errors will be detected before
        //  NullPointerException or NumberFormatException are thrown!
        return Integer.parseInt(id);
    }
    private String ssanitize(String s) {
        if (s != null) {
            return s.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;");
        } else {
            return null;
        }
    }
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (id == null || id.length() == 0) {
            errors.add("Id is required");
            return errors;
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            errors.add("Id is not an integer");
        }

        return errors;
    }
    
    @InputType("hidden")
    public void setId(String id) { this.id = id; }

    public void validate() {
        super.validate();

        if (hasValidationErrors()) {
            return;
        }

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            this.addFormError("Id is not an integer");
        }
    }
}
