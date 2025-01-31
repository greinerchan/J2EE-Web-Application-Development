package edu.cmu.cs.webapp.todolist5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.todolist5.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model) {
    }

    public String getName() {
        return "logout.do";
    }

    public String performPost(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);

        return "login.jsp";
    }
}
