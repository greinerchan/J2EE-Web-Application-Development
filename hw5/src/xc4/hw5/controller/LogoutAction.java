package xc4.hw5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import xc4.hw5.model.Model;
import xc4.hw5.model.UserDAO;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
	
	UserDAO userDAO;
    public LogoutAction(Model model) {
    	userDAO = model.getUserDAO();
    }

    public String getName() {
        return "logout.do";
    }

    public String performPost(HttpServletRequest request) {
    	try { 
	        request.setAttribute("users", userDAO.getUsers());
		} catch (RollbackException e) {
			e.printStackTrace();
		}
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);

        return "login.jsp";
    }
}
