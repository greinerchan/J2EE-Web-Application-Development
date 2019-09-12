package xc4.hw5.controller;

import xc4.hw5.model.Model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import xc4.hw5.databean.CommentBean;
import xc4.hw5.databean.PostBean;
import xc4.hw5.databean.User;
import xc4.hw5.formbean.IdForm;
import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.UserDAO;

public class CommentDeleteAction extends Action {
    private CommentDAO commentDAO;
    private PostDAO postDAO;
    private UserDAO userDAO;

    public CommentDeleteAction(Model model) {
    		commentDAO = model.getCommentDAO();
    		postDAO = model.getPostDAO();
    		userDAO = model.getUserDAO();
    }

    public String getName() {
        return "commentDelete.do";
    }
    
    public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        try {
            IdForm form = new IdForm(request);
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "error.jsp";
            }
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("user", user);
            request.setAttribute("users", userDAO.getUsers());
            int id = form.getIdAsInt();
            homeDelete(id, user.getEmail());
            CommentBean[] comment = commentDAO.getComments(request);
            PostBean[] post = postDAO.getPosts(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());
            return "home.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
    private void homeDelete(int id, String userEmail) throws RollbackException {
		
		try {
			Transaction.begin();
			CommentBean comment = commentDAO.read(id); 
			CommentBean[] comments = commentDAO.match(MatchArg.contains("visitorEmail", userEmail));
			Boolean isHome = false;
            if (comment == null) {
                throw new RollbackException("Comment Not Exist");
            }
            
            for (CommentBean homeComments : comments) {
            	if (homeComments.getId() != id) {
            		continue;
            	}
            	isHome = true;
            }
            
            if (!isHome) {
                throw new RollbackException("You Are Not Owner of this Comment");    
            }
            commentDAO.delete(id);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
