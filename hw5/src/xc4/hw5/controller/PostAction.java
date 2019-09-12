package xc4.hw5.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import xc4.hw5.databean.PostBean;
import xc4.hw5.databean.User;
import xc4.hw5.formbean.PostForm;
import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.Model;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.UserDAO;

public class PostAction extends Action {
    private PostDAO postDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    public PostAction(Model model) {
    		postDAO = model.getPostDAO();
    		userDAO = model.getUserDAO();
    		commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "post.do";
    }
    
    public String performPost(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        if (request.getSession().getAttribute("user") == null) {
            return "login.do";
        }
        try {  
            request.setAttribute("users", userDAO.getUsers());
        	User user = (User) request.getSession().getAttribute("user");
        	request.setAttribute("user", user);
        	request.setAttribute("comments", commentDAO.getComments(request));
        	request.setAttribute("posts", postDAO.getPosts(request));
            PostForm form = new PostForm(request);
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "error.jsp";
            }
            String userName= user.getFirstName() + " " + user.getLastName();
            PostBean post = new PostBean();
            Date date = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-yyyy hh:mm aa");
            String dateFormate = formattedDate.format(date);
            post.setPosts((form.getPosts()));
            post.setDate(dateFormate);
            post.setEmail(user.getEmail());
            post.setUserName(userName);
            post.setRealDate(date);

            postDAO.create(post);

            
            request.setAttribute("form", form);

        	request.setAttribute("users", userDAO.getUsers());
            request.setAttribute("comments", commentDAO.getComments(request));
            request.setAttribute("posts", postDAO.getPosts(request));
            

            return "home.do";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
