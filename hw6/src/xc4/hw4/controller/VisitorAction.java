package xc4.hw4.controller;

import xc4.hw4.formbean.UserForm;
import xc4.hw4.model.Model;
import xc4.hw4.model.UserDAO;
import org.genericdao.RollbackException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class VisitorAction extends Action {

    private UserDAO userDAO;

    public VisitorAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "visitor.do";
    }

    public String performPost(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
            UserForm form = new UserForm(request);
            request.setAttribute("form", form);
            
            if (!form.isPresent()) {
                return "visitor.jsp";
            }
            if (form.getButton().equals("Visitor")) {
                return "visitor-login.jsp";
            }
            request.setAttribute("users", userDAO.getUsers());

            return "visitor.jsp";
        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "visitor.jsp";
        } 
    }
}