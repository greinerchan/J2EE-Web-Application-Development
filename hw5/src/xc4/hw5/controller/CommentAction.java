package xc4.hw5.controller;

import xc4.hw5.model.Model;
import xc4.hw5.model.PostDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import xc4.hw5.databean.User;
import xc4.hw5.databean.CommentBean;
import xc4.hw5.databean.PostBean;
import xc4.hw5.formbean.CommentForm;
import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.UserDAO;

public class CommentAction extends Action {
    private PostDAO postDAO;
    private CommentDAO commentDAO;
    private UserDAO userDAO;

    public CommentAction(Model model) {
    		commentDAO = model.getCommentDAO();
    		userDAO = model.getUserDAO();
    		postDAO = model.getPostDAO();
    }

    public String getName() {
        return "comment.do";
    }
    public String performGet(HttpServletRequest request) {
    	return performPost(request);
    }
    public String performPost(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        String email = sanitize(request.getParameter("email"));
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        try { 
        	request.setAttribute("comments", commentDAO.getVisitorComments(email));
        	request.setAttribute("posts", postDAO.getPosts(email));
            request.setAttribute("users", userDAO.getUsers());
            CommentForm form = new CommentForm(request);
            request.setAttribute("form", form);
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "error.jsp";
            }
        	User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("user", user);
        	String userName= user.getFirstName() + " " + user.getLastName();
            Date date = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String dateFormate = formattedDate.format(date);
            int postId = Integer.parseInt(form.getCommentId());
            CommentBean comment = new CommentBean();
            comment.setComments((form.getComments()));
            comment.setDate(dateFormate);
            comment.setVisitorEmail(email);
            comment.setEmail(user.getEmail());
            comment.setCommentId(postId);
            comment.setUserName(userName);
            comment.setRealDate(date);
            
            createBean(comment, postId);

        	request.setAttribute("users", userDAO.getUsers());
            request.setAttribute("comments", commentDAO.getVisitorComments(email));
            request.setAttribute("posts", postDAO.getPosts(email));

            return "visitor-login.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
	private void createBean(CommentBean bean, int postId) throws RollbackException {
		try {
			Transaction.begin();
			//postId is the same with the commentId
			PostBean post = postDAO.read(postId); 
			if (post == null) {
				throw new RollbackException("Post Not Exist");
			}
			commentDAO.create(bean);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
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
