package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.InputType;
import org.formbeanfactory.Label;

@FieldOrder("newPassword,confirmPassword")
public class SetPasswordForm extends FormBean {
    private String confirmPassword;
    private String newPassword;
    private String userName;

    public SetPasswordForm() {
        super();
    }

    public SetPasswordForm(HttpServletRequest request) {
        super(request);
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getUserName() {
        return userName;
    }

    @Label("Confirm Pwd")
    @InputType("password")
    public void setConfirmPassword(String s) {
        confirmPassword = s.trim();
    }

    @InputType("password")
    public void setNewPassword(String s) {
        newPassword = s.trim();
    }

    @InputType("hidden")
    public void setUserName(String s) {
        userName = s.trim();
    }

    public void validate() {
        super.validate();

        if (hasValidationErrors()) {
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            addFieldError("confirmPassword", "Passwords do not match");
        }
    }
}
