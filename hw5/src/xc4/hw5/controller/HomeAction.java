package xc4.hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import xc4.hw5.databean.CommentBean;
import xc4.hw5.databean.PostBean;
import xc4.hw5.databean.User;
import xc4.hw5.model.Model;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.UserDAO;

public class HomeAction extends Action {
	private PostDAO postDAO;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	
    public HomeAction(Model model) {
    		postDAO = model.getPostDAO();
    		commentDAO = model.getCommentDAO();
    		userDAO = model.getUserDAO();
    }
    
    public String getName() {
        return "home.do";
    }
    
    public String performGet(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
    	try {
            request.setAttribute("users", userDAO.getUsers());
            CommentBean[] comment;
            PostBean[] post;
            comment = commentDAO.getComments(request);
            post = postDAO.getPosts(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());           
            return performPost(request);
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }       
    }
    
    public String performPost(HttpServletRequest request) {
        User user = (User) request.getSession(false).getAttribute("user");
        request.setAttribute("user", user);
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        // If user is NOT logged in, redirect to login.do
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        try {
            request.setAttribute("users", userDAO.getUsers());
            CommentBean[] comment;
            PostBean[] post;
            comment = commentDAO.getComments(request);
            post = postDAO.getPosts(request);
            request.setAttribute("posts", post);
            request.setAttribute("comments", comment);
            request.setAttribute("users", userDAO.getUsers());
            
            return ("home.jsp");
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
