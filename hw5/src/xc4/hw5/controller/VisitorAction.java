package xc4.hw5.controller;

import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.Model;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.UserDAO;

import org.genericdao.RollbackException;

import xc4.hw5.formbean.UserForm;
import xc4.hw5.controller.Action;
import xc4.hw5.databean.CommentBean;
import xc4.hw5.databean.PostBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class VisitorAction extends Action {

    private CommentDAO commentDAO;
    private UserDAO userDAO;
    private PostDAO postDAO;

    public VisitorAction(Model model) {
        commentDAO = model.getCommentDAO();
        userDAO = model.getUserDAO();
        postDAO = model.getPostDAO();
    }

    public String getName() {
        return "visitor.do";
    }
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        String email = sanitize(request.getParameter("email"));
        try {
            UserForm form = new UserForm(request);
            request.setAttribute("form", form);
            request.setAttribute("users", userDAO.getUsers());
            CommentBean[] comment;
            PostBean[] post;
            post = postDAO.getPosts(email);
            comment = commentDAO.getComments(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());
            if (!form.isPresent()) {
                return "visitor.jsp";
            }
            return "visitor.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "visitor.jsp";
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