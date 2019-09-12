package edu.cmu.cs.webapp.todolist6.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

@FieldOrder("firstName, lastName, userName, password, confirm")
public class RegisterForm extends FormBean {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirm;
    
    public RegisterForm() {
    	super();
    }
    
    public RegisterForm(HttpServletRequest request) {
    	super(request);
    }
    
    public String getUserName()  { return userName;   }
    public String getPassword()  { return password;   }
    public String getFirstName() { return firstName;  }
    public String getLastName()  { return lastName;   }
	public String getConfirm()   { return confirm; }

	public void setFirstName(String s) { firstName = s.trim(); }
	public void setLastName(String s)  { lastName = s.trim();  }
	public void setUserName(String s)  { userName = s.trim();  }
	
    @InputType("password")
    public void setPassword(String s)  { password = s.trim();  }

    @InputType("password")
    @Label("Confirm Password:")
	public void setConfirm(String s)   { confirm= s.trim();    }

    public void validate() {
        super.validate();
        if (hasValidationErrors()) {
            return;
        }
        
        // No validation errors from super class, so all required fields are present
        
        if (userName.matches(".*[<>\"].*")) {
            this.addFieldError("userName", "May not contain angle brackets or quotes");
        }

        if (!password.equals(confirm)) {
            this.addFieldError("password", "Passwords do not match");
            this.addFieldError("confirm",  "Passwords do not match");
        }
    }
}
