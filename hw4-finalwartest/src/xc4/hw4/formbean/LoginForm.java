package xc4.hw4.formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String button;
	public LoginForm(HttpServletRequest request) {
		password = sanitize(request.getParameter("password"));
		email = sanitize(request.getParameter("email"));
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
	public String getButton() {
		return button;
	}

	public boolean isPresent() {
		return button != null;
	}
	public boolean notExist() {
		return !button.equals("User")  && !button.equals("Home") && !button.equals("Login") && !button.equals("Register") && !button.equals("Submit");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.trim().length() == 0) {
			errors.add("Email is Empty");
		} else if (!isEmailValid(email)) {
			errors.add("Email Address is Invalid");
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is Empty");
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
