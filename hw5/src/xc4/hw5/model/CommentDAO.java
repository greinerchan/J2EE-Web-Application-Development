package xc4.hw5.model;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import xc4.hw5.databean.CommentBean;


public class CommentDAO extends GenericDAO<CommentBean> {

    public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(CommentBean.class, tableName, cp);
    }
	
    public CommentBean[] getComments(String email) throws RollbackException {
		CommentBean[] posts = match(MatchArg.equals("email", email));		
		Arrays.sort(posts, (CommentBean comment1, CommentBean comment2) -> comment1.getRealDate().compareTo(comment2.getRealDate()));
		return posts;
    }
    public CommentBean[] getVisitorComments(String visitorEmail) throws RollbackException {
		CommentBean[] posts = match(MatchArg.equals("visitorEmail", visitorEmail));		
		Arrays.sort(posts, (CommentBean comment1, CommentBean comment2) -> comment1.getRealDate().compareTo(comment2.getRealDate()));
		return posts;
    }
    
	public CommentBean[] getComments(HttpServletRequest request) throws RollbackException {
		CommentBean[] comments = match();	
		Arrays.sort(comments, (CommentBean comment1, CommentBean comment2) -> comment1.getRealDate().compareTo(comment2.getRealDate()));

		return comments;
	}
    public CommentBean[] getComments() throws RollbackException {
        return match();
    }
}
