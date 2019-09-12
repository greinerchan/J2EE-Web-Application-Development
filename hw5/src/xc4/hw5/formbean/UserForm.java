package xc4.hw5.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class UserForm {
	private String firstName;
	private String lastName;
	private String button;

	public UserForm(HttpServletRequest request) {
		button = sanitize(request.getParameter("button"));
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getButton() {
		return button;
	}

	public boolean isPresent() {
		return button != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (!isPresent()) {
			errors.add("Button is Required");
		}
		if (!button.equals("User") && !button.equals("Visitor") && !button.equals("Home") 
				&& !button.equals("commentButton")) {
			errors.add("Invalid Button"); 
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
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
