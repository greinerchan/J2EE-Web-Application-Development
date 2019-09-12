package edu.cmu.cs.webapp.todolist6.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;

@FieldOrder("userName,password")
public class LoginForm extends FormBean {
    private String userName;
    private String password;
    private String action;
    
    public LoginForm() {
    	super();
    }
    
    public LoginForm(HttpServletRequest request) {
    	super(request);
    }
    
    public String getUserName()  { return userName; }
    public String getPassword()  { return password; }
	
    public void setUserName(String s)  { userName = s.trim(); }
    @InputType("password")
    public void setPassword(String s)  { password = s.trim(); }
}
