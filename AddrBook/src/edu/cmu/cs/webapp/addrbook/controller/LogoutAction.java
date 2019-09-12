package edu.cmu.cs.webapp.addrbook.controller;

import javax.servlet.http.HttpServletRequest;

import edu.cmu.cs.webapp.addrbook.model.Model;

public class LogoutAction extends Action {

	public LogoutAction(Model model) {
	}

	public String getName() { return "logout.do"; }

	public String performGet(HttpServletRequest request) {
        request.getSession().setAttribute("user",null);
        return "login.do";
    }
}
