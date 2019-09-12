package xc4.hw5.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class VisitorForm {
	private String firstName;
	private String lastName;
	private String visitor;

	public VisitorForm(HttpServletRequest request) {
		visitor = sanitize(request.getParameter("visitor"));
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getVisitor() {
		return visitor;
	}

	public boolean isPresent() {
		return visitor != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (!isPresent())
			errors.add("Button is Required");
		if (!visitor.equals("User") && !visitor.equals("Visitor") && !visitor.equals("Home")) {
			errors.add("Invalid button"); 
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
