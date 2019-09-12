package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

@FieldOrder("userName,password,confirmPassword")
public class CreateUserForm extends FormBean {
    private String userName;
    private String password;
    private String confirmPassword;

    public CreateUserForm() {
        super();
    }

    public CreateUserForm(HttpServletRequest request) {
        super(request);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setUserName(String s) {
        userName = s.trim();
    }

    @InputType("password")
    public void setPassword(String s) {
        password = s.trim();
    }

    @Label("Confirm Pwd:")
    @InputType("password")
    public void setConfirmPassword(String s) {
        confirmPassword = s.trim();
    }

    public void validate() {
        super.validate();

        if (hasValidationErrors()) {
            return;
        }

        if (userName.matches(".*[<>\"].*")) {
            addFieldError("userName", "User Name cannot contain angle brackets or quotes");
        }

        if (!password.equals(confirmPassword)) {
            addFieldError("confirmPassword", "Passwords do not match");
        }
    }
}
