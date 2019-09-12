package xc4.hw5.model;



import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import xc4.hw5.databean.User;

public class UserDAO extends GenericDAO<User> {
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(User.class, tableName, cp);
    }
    public void addUser(User user) throws RollbackException {
		try {
			Transaction.begin();
			User[] a = match(MatchArg.containsIgnoreCase("email", user.getEmail()));
			if (a.length > 0) {
				throw new RollbackException("already has user");
			}
			super.create(user);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	public User[] getUsers() throws RollbackException {

		// Calls GenericDAO's match() method.
		// This no match constraint arguments, match returns all the Item beans
		User[] Users = match();
		

		return Users;
	}
}
