package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Optional;

@FieldOrder("userName,password")
public class LoginForm extends FormBean {
    private String userName;
    private String password;
    private String redirect;

    public LoginForm() {
        super();
    }

    public LoginForm(HttpServletRequest request) {
        super(request);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setUserName(String s) {
        userName = s.trim();
    }

    @InputType("password")
    public void setPassword(String s) {
        password = s.trim();
    }

    @Optional
    @InputType("hidden")
    public void setRedirect(String s) {
        redirect = s.trim();
    }
}
