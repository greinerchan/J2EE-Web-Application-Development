package xc4.hw5.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import xc4.hw5.model.CommentDAO;
import xc4.hw5.model.PostDAO;
import xc4.hw5.model.UserDAO;

public class Model {
    private UserDAO userDAO;
	private PostDAO postDAO;
	private CommentDAO commentDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            userDAO = new UserDAO(pool, "xc4_user");
            postDAO = new PostDAO(pool, "xc4_post");
            commentDAO = new CommentDAO(pool, "xc4_comment");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }
 
    public PostDAO getPostDAO() {
    	return postDAO;
    }
    
    public CommentDAO getCommentDAO() {
    	return commentDAO;
    }
}
