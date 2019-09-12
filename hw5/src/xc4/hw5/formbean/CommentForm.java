package xc4.hw5.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class CommentForm {
	private String comment;
	private String button;
	private String commentId;

	public CommentForm(HttpServletRequest request) {
		comment = sanitize(request.getParameter("comment"));
		button = sanitize(request.getParameter("button"));
		commentId = sanitize(request.getParameter("id"));
	}
	
    public String getComments() {
        return comment;
    }

    public String getButton() {
        return button;
    }
    public String getCommentId() {
        return commentId;
    }
    

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        if (comment == null || comment.trim() == "") {
            errors.add("Comment is Empty");
        }
              
        if (button == null || !button.equals("commentButton")) {
            errors.add("Button is Invalid");
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
