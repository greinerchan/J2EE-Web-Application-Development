package xc4.hw5.model;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import xc4.hw5.databean.PostBean;
import xc4.hw5.databean.User;

public class PostDAO extends GenericDAO<PostBean> {
	
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostBean.class, tableName, cp);
	}
	
	public PostBean[] getPosts(HttpServletRequest request) throws RollbackException {
		User currUser = (User) request.getSession().getAttribute("user");
		String email = currUser.getEmail();
		PostBean[] posts = match(MatchArg.equals("email", email));
		Arrays.sort(posts, (PostBean post1, PostBean post2) -> post2.getRealDate().compareTo(post1.getRealDate()));
		return posts;
	}
    public PostBean[] getPosts(String email) throws RollbackException {
		PostBean[] posts = match(MatchArg.equals("email", email));		
		Arrays.sort(posts, (PostBean post1, PostBean post2) -> post2.getRealDate().compareTo(post1.getRealDate()));
		return posts;
    }
    public PostBean[] getPosts() throws RollbackException {
		return match();		
    }

}
