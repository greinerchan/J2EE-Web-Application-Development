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

public class PostDeleteAction extends Action {
    private CommentDAO commentDAO;
    private PostDAO postDAO;
    private UserDAO userDAO;

    public PostDeleteAction(Model model) {
    		commentDAO = model.getCommentDAO();
    		postDAO = model.getPostDAO();
    		userDAO = model.getUserDAO();
    }

    public String getName() {
        return "postDelete.do";
    }
    
    public String performPost(HttpServletRequest request) {
        // If user is NOT logged in, redirect to login.do
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "login.do";
        }

        try {
            CommentBean[] comment = commentDAO.getComments(request);
            PostBean[] post = postDAO.getPosts(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());
            IdForm form = new IdForm(request);          
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "error.jsp";
            }
            int id = form.getIdAsInt();
            deletePost(id, user.getEmail());
            comment = commentDAO.getComments(request);
            post = postDAO.getPosts(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());
            return "home.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
    private void deletePost(int id, String userEmail) throws RollbackException {		
		try {
			Transaction.begin();
			
			PostBean post = postDAO.read(id); 
			
            if (post == null) {
                throw new RollbackException("Post Not Exist");
            }

            if (!userEmail.equals(post.getEmail())) {
                throw new RollbackException("You are not owner of this post");    
            }
  

            CommentBean[] comments =  commentDAO.match(MatchArg.equals("commentId", id));
            postDAO.delete(id);
            for (CommentBean comment : comments) {
            	commentDAO.delete(comment.getId());
            }
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
