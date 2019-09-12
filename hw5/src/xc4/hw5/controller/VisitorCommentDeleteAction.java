package xc4.hw5.controller;

import xc4.hw5.model.Model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import xc4.hw5.databean.CommentBean;
import xc4.hw5.databean.User;
import xc4.hw5.formbean.IdForm;
import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.UserDAO;

public class VisitorCommentDeleteAction extends Action {
    private CommentDAO commentDAO;
    private PostDAO postDAO;
    private UserDAO userDAO;

    public VisitorCommentDeleteAction(Model model) {
    		commentDAO = model.getCommentDAO();
    		postDAO = model.getPostDAO();
    		userDAO = model.getUserDAO();
    }

    public String getName() {
        return "visitorCommentDelete.do";
    }
    
    public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        String email = sanitize(request.getParameter("email"));
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        try {
            request.setAttribute("posts", postDAO.getPosts(email));
            request.setAttribute("comments", commentDAO.getVisitorComments(email));
            request.setAttribute("users", userDAO.getUsers());
            IdForm form = new IdForm(request);
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "error.jsp";
            }
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("user", user);
            request.setAttribute("users", userDAO.getUsers());
            int id = form.getIdAsInt();
            visitorDelete(id, user.getEmail());
            request.setAttribute("posts", postDAO.getPosts(email));
            request.setAttribute("comments", commentDAO.getVisitorComments(email));
            request.setAttribute("users", userDAO.getUsers());
            return "visitor-login.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
    private void visitorDelete(int id, String userEmail) throws RollbackException {
		try {
			Transaction.begin();
			CommentBean comment = commentDAO.read(id); 
			
            if (comment == null) {
                throw new RollbackException("Comment Not Exist");
            }

            if (!userEmail.equals(comment.getEmail())) {
                throw new RollbackException("You Are Not Owner of this Comment");    
            }

            commentDAO.delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
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
