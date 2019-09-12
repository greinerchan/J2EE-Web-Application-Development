package edu.cmu.cs.webapp.threads;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todo")
public class ToDoListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // The Data Access Objects
    private BrokenItemDAO itemDAO = new BrokenItemDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		outputHtml(request, response, null);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Look at the parameters. Add or delete item as requested
		String action = request.getParameter("action");

		// Message to be displayed indicating status of requested action
		String message = null;

		if (action.equals("Add to Top")) {
			Item newItem = new Item();
			newItem.text = request.getParameter("addText");
			newItem.ipAddress = request.getRemoteAddr();
			itemDAO.addToTop(newItem);
			message = "Added item in position 1";
		} else if (action.equals("Add to Bottom")) {
			Item newItem = new Item();
			newItem.text = request.getParameter("addText");
			newItem.ipAddress = request.getRemoteAddr();
			int position = itemDAO.addToBottom(newItem);
			message = "Added item in position " + position;
		} else if (action.equals("Delete")) {
			itemDAO.delete(Integer.parseInt(request.getParameter("deleteNum")) - 1);
			message = "Deleted item in position " + request.getParameter("deleteNum");
		}

		outputHtml(request, response, message);
	}
    
    private void outputHtml(HttpServletRequest request, HttpServletResponse response, String message)
    			throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html>");
        out.println("<html lang=\"en\">");

        // Generate the HTML <head>
        out.println("  <head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>To Do List</title>");
        out.println("  </head>");

        out.println("<body>");
        out.println("<h2>Web App To Do List</h2>");

        // Generate an HTML <form> to get data from the user
        out.println("<form method=\"POST\">");
        out.println("  <table>");
        out.println("  <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("  <tr>");
        out.println("    <td>Item to Add:</td>");
        out.println("    <td colspan=\"2\"><input type=\"text\" size=\"40\" name=\"addText\"/></td>");
        out.println("  </tr>");
        out.println("  <tr>");
        out.println("    <td></td>");
        out.println("    <td><input type=\"submit\" name=\"action\" value=\"Add to Top\"></td>");
        out.println("    <td><input type=\"submit\" name=\"action\" value=\"Add to Bottom\"></td>");
        out.println("  </tr>");
        out.println("  <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("  <tr>");
        out.println("    <td>Item Number:</td>");
        out.println("    <td><input type=\"text\" size=\"10\" name=\"deleteNum\"></td>");
        out.println("    <td><input type=\"submit\" name=\"action\" value=\"Delete\"></td>");
        out.println("  </tr>");
        out.println("  <tr><td colspan=\"3\"><hr/></td></tr>");
        out.println("  </table>");
        out.println("</form>");

        if (message != null) {
        		out.println("<p style=\"color:red\">");
        		out.println(message);
        		out.println("</p>");
        }
        
        String displayOption = request.getParameter("display");
        boolean displaySanitized = displayOption != null && displayOption.equals("sanitize");
        boolean displayDetails   = displayOption != null && displayOption.equals("detail");

        out.println("<p>The list now has " + itemDAO.size() + " items.</p>");
        out.println("<table>");
        for (int i = 0; i < itemDAO.size(); i++) {
            out.println("<tr>");
            try {
                Item item = itemDAO.getItem(i);
                out.println("<td><span style=\"font-size: x-large\">" + (i + 1) + ".</span></td>");
                out.println("<td>");
                out.println("  <span style=\"font-size: x-large\">");
                if (displaySanitized || displayDetails) {
                    out.println(sanitize(item.text));
                } else {
                    out.println(item.text);
                }
                out.println("  </span>");
                if (displayDetails) {
                    out.println(" (uniqueId=" + item.uniqueId + ", ipAddr=" + item.ipAddress + ")");
                }
                out.println("</td>");
            } catch (Exception e) {
                out.println("<td><span style=\"font-size: x-large\">" + (i + 1) + ".</span></td>");
                out.println("<td><span style=\"font-size: x-large\">" + e.getMessage() + "</span></td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }

    private String sanitize(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}
