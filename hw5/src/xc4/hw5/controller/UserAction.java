package xc4.hw5.controller;

import xc4.hw5.databean.User;
import xc4.hw5.formbean.UserForm;
import xc4.hw5.model.Model;
import xc4.hw5.model.UserDAO;
import org.genericdao.RollbackException;

import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.PostDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserAction extends Action {

    private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;
    public UserAction(Model model) {
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "user.do";
    }
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }

    public String performPost(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        String email = sanitize(request.getParameter("email"));
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        try {
            UserForm form = new UserForm(request);
            request.setAttribute("form", form);
            request.setAttribute("comments", commentDAO.getComments(request));
			User[] users;
			users = userDAO.getUsers();		
			request.setAttribute("users", users);
			request.setAttribute("posts", postDAO.getPosts(email));
            if (!form.isPresent()) {
                return "visitor-login.jsp";
            }
            if (form.getButton().equals("User")) {
                return "visitor-login.jsp";
            }
            if (form.getButton().equals("Home")) {
                return "home.jsp";
            }
            request.setAttribute("users", userDAO.getUsers());

            return "visitor-login.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "visitor-login.jsp";
        } 
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