package edu.cmu.cs.webapp.addrbook.formbean;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBean;

public class SearchForm extends FormBean {
    private String last;
    private String first;

    public SearchForm() {
        super();
    }

    public SearchForm(HttpServletRequest request) {
        super(request);
    }

    public String getLast() {
        return last;
    }

    public String getFirst() {
        return first;
    }

    public void setLast(String s) {
        last = s.trim();
    }

    public void setFirst(String s) {
        first = s.trim();
    }

    public void validate() {
        // Do not call super.validate()
        // Our validation is completely different
        // and done here.

        // Later processing wants empty strings and no null strings, so clean them up.
        if (last == null)
            last = "";
        if (first == null)
            first = "";
    }

}
