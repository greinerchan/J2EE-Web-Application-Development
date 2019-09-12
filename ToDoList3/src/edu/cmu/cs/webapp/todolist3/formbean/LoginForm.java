package edu.cmu.cs.webapp.todolist3.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
    private String userName;
    private String password;

    public LoginForm(HttpServletRequest request) {
        userName = request.getParameter("userName");
        password = request.getParameter("password");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (userName == null || userName.length() == 0) {
            errors.add("User Name is required");
        }
        
        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }

        return errors;
    }
}
