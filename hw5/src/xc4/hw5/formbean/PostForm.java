

package xc4.hw5.formbean; 


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PostForm {
	private String post;
	private String button;
	private String postId;

	public PostForm(HttpServletRequest request) {
		post = sanitize(request.getParameter("post"));
		button = sanitize(request.getParameter("button"));
		postId = sanitize(request.getParameter("id"));
	}
	
    public String getPosts() {
        return post;
    }

    public String getButton() {
        return button;
    }
    public String getPostId() {
        return postId;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (post == null || post.length() == 0) {
            errors.add("Post is Empty");
        }        
        if (button == null) {
            errors.add("Button Invalid");
        }
        if (errors.size() > 0) {
            return errors;
        }
        if (!button.equals("postButton")) {
            errors.add("Button Invalid");
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
