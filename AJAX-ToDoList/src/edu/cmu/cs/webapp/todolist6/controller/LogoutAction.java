package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.cmu.cs.webapp.todolist6.formbean.LoginForm;
import edu.cmu.cs.webapp.todolist6.model.Model;

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
        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        request.setAttribute("form", new LoginForm());
        return "login.jsp";
    }
}
