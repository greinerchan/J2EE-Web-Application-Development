package xc4.hw4.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
    private UserDAO userDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

            userDAO = new UserDAO(pool, "xc4_user");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }
}
