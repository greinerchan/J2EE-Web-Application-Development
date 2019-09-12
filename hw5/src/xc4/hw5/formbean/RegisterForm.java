package xc4.hw5.formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String button;
    
    public RegisterForm(HttpServletRequest request) {
        firstName = sanitize(request.getParameter("firstName"));
        lastName = sanitize(request.getParameter("lastName"));
        password = sanitize(request.getParameter("password"));
        email = sanitize(request.getParameter("email"));
        confirmPassword = sanitize(request.getParameter("confirmPassword"));
		button = sanitize(request.getParameter("button"));
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
    	return email;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmationPassword() {
        return confirmPassword;
    }
	public String getButton() {
		return button;
	}
	public boolean isPresent() {
		return button != null;
	}
	public boolean notExist() {
		return !button.equals("User") && !button.equals("Visitor") && !button.equals("Home") && !button.equals("Login") && !button.equals("Register") && !button.equals("Submit");
	}

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if (!button.equals("User") && !button.equals("Visitor") && !button.equals("Home") && !button.equals("Login") && !button.equals("Register") && !button.equals("Submit")) {
			errors.add("Invalid button"); 
		}
		if (!isPresent()) {
			errors.add("Button is invalid");
		}

        if (lastName == null || lastName.trim().length() == 0) {
            errors.add("Last Name is Empty");
        } else if (lastName.matches(".*[<>\"].*")) {
			errors.add("Last Name may not contain angle brackets or quotes");
		}
        if (firstName == null ||firstName.trim().length() == 0) {
            errors.add("first Name is Empty");
        } else if (firstName.matches(".*[<>\"].*")) {
			errors.add("First Name may not contain angle brackets or quotes");
		}   
        if (password == null || password.length() == 0) {
            errors.add("Password is Empty");
        }
        if (confirmPassword == null || confirmPassword.length() == 0) {
            errors.add("Confirm Password is Empty");
        } else if (!password.equals(confirmPassword)) {
            errors.add("Password Does not Match.");
        }  
		if (email == null || email.length() == 0) {
			errors.add("Email Address is Empty");
		} else if (!isEmailValid(email)) {
			errors.add("Email Address is invalid");
		}
        if (errors.size() > 0) {
            return errors;
        }

        return errors;
    }

    public static boolean isEmailValid(String email) { 
        String rule = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";                          
        Pattern pattern = Pattern.compile(rule); 
        if (email == null) 
            return false; 
        return pattern.matcher(email).matches(); 
    } 
    private String sanitize(String s) {
        if (s != null) {
            return s.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;");
        } else {
            return null;
        }
    }
}
