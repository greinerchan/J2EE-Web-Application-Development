package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.Field;
import org.formbeanfactory.FormBean;

public class AdvancedSearchForm extends FormBean {
    private String lastName;
    private String firstName;
    private String anyPhone;
    private String email;
    private String additional;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String receivedCards;
    private String sentCards;
    

    public AdvancedSearchForm() {
        super();
    }

    public AdvancedSearchForm(HttpServletRequest request) {
        super(request);
    }

    public String getAdditional() {
        return additional;
    }

    public String getAddress() {
        return address;
    }

    public String getAnyPhone() {
        return anyPhone;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getReceivedCards() {
        return receivedCards;
    }

    public String getSentCards() {
        return sentCards;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setAdditional(String s) {
        additional = s.trim();
    }

    public void setAddress(String s) {
        address = s.trim();
    }

    public void setAnyPhone(String s) {
        anyPhone = s.trim();
    }

    public void setCity(String s) {
        city = s.trim();
    }

    public void setCountry(String s) {
        country = s.trim();
    }

    public void setEmail(String s) {
        email = s.trim();
    }

    public void setFirstName(String s) {
        firstName = s.trim();
    }

    public void setLastName(String s) {
        lastName = s.trim();
    }

    public void setReceivedCards(String s) {
        receivedCards = s.trim();
    }

    public void setSentCards(String s) {
        sentCards = s.trim();
    }

    public void setState(String s) {
        state = s.trim();
    }

    public void setZip(String s) {
        zip = s.trim();
    }

    public void validate() {
        // Don't call super.validate()

        // All fields optional, but one must be present
        for (Field f : getAllFields()) {
            String value = f.getValue();
            if (value != null && value.length() > 0) {
                return;
            }
        }

        addFormError("No form data sent with this request.");
    }
}
