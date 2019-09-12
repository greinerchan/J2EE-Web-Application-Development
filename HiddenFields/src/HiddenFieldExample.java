import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HiddenFieldExample")
public class HiddenFieldExample extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String what = request.getParameter("what");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Hidden Field Example</title>");
        out.println("</head>");
        out.println("<body>");
        if (firstname == null) {
            out.println("<form action=\"HiddenFieldExample\" method=\"POST\">");
            out.println("    What's your firstname: <input type=\"text\" size=\"40\" name=\"firstname\" autofocus>");
            out.println("    <input type=\"submit\">");
            out.println("</form>");
        } else if (lastname == null) {
            out.println("<form action=\"HiddenFieldExample\" method=\"POST\">");
            out.println("    What's your lastname: <input type=\"text\" size=\"40\" name=\"lastname\" autofocus>");
            out.println("    <input type=\"hidden\" name=\"firstname\" value=\"" + firstname + "\">");
            out.println("    <input type=\"submit\">");
            out.println("</form>");
        } else if (what == null) {
            out.println("<form action=\"HiddenFieldExample\" method=\"POST\" />");
            out.println("    What do you want to buy: <input type=\"text\" size=\"40\" name=\"what\" autofocus>");
            out.println("    <input type=\"hidden\" name=\"firstname\" value=\"" + firstname + "\">");
            out.println("    <input type=\"hidden\" name=\"lastname\" value=\"" + lastname + "\">");
            out.println("    <input type=\"submit\">");
            out.println("</form>");
        } else {
            out.println("<h1>Sorry, " + firstname + " " + lastname + ".</h1>");
            out.println("<h1>We're out of " + what + "!</h1>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
