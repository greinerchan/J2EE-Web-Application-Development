package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

public class DeleteUserForm extends FormBean {
	private String button;
	private String userName;

    public DeleteUserForm() {
        super();
    }

    public DeleteUserForm(HttpServletRequest request) {
        super(request);
    }
	public String getButton()   { return button;   }
	public String getUserName() { return userName; }
	
	public void setButton(String s)   { button   = s.trim(); }
	@InputType("hidden")
	public void setUserName(String s) { userName = s.trim(); }
}
