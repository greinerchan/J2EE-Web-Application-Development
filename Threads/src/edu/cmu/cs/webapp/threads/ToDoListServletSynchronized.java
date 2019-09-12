package edu.cmu.cs.webapp.threads;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/correct-todolist")
public class ToDoListServletSynchronized extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // The Data Access Objects
    private ItemDAO itemDAO = new ItemDAO();


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    		outputHtml(request, response, null);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Look at the parameters. Add or delete item as requested
        String action = request.getParameter("action");
        
        if (action == null) {
        		String message = "Missing action parameter";
        		outputHtml(request, response, message);
        		return;
        	}
        
        if (action.startsWith("Add")) {
        		String message = add(request);
        		outputHtml(request, response, message);
        		return;
        }
        
        if (action.equals("Delete")) {
        		String message = delete(request);
        		outputHtml(request, response, message);
        		return;
        }
        
    		String message = "Invalid action: " + action;
    		outputHtml(request, response, message);
    }
    
    private String add(HttpServletRequest request) {
        String action = request.getParameter("action");
        assert(action != null);
        
        String itemText = request.getParameter("addText");
        
        if (itemText == null || itemText.length() == 0) {
        		return "Item to Add is required";
        }
        
        Item newItem = new Item();
        newItem.text = itemText;
        newItem.ipAddress = request.getRemoteAddr();

        if (action.equals("Add to Top")) {
            itemDAO.addToTop(newItem);
            return "Added item in position 1";
        }
        
        if (action.equals("Add to Bottom")) {
            int position = itemDAO.addToBottom(newItem);
            return "Added item in position " + position;
        }
        
        return "Invalid add action: " + action;
    }

    private String delete(HttpServletRequest request) {
    		String deleteStr = request.getParameter("deleteNum");
    		if (deleteStr == null) {
    			return "Unique ID to delete must be provided.";
    		}
    		
    		try {
    			int deleteNum = Integer.parseInt(deleteStr);
    			itemDAO.delete(deleteNum);
    			return "Deleted item with unique id = " + deleteNum;
    		} catch (NumberFormatException e) {
    			return "ID to delete was not a number: \"" + deleteStr + "\"";
    		}
    }

    private void outputHtml(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!doctype html>");
        out.println("<html>");

        // Generate the HTML <head>
        out.println("  <head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>To Do List</title>");
        out.println(" </head>");

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
        out.println("    <td>Item Unique ID:</td>");
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

        Item[] items = itemDAO.getItems();
        out.println("<p>The list now has " + items.length + " items.</p>");
        out.println("<table>");
        for (int i = 0; i < items.length; i++) {
            out.println("<tr>");
            Item item = items[i];
            out.println("<td><span style=\"font-size: x-large\">" + (i + 1) + ".</span></td>");
            out.println("<td><span style=\"font-size: x-large\">"
                    + sanitize(item.text) + "</span> (uniqueId="
                    + item.uniqueId + ", ipAddr=" + item.ipAddress + ")</td>");
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
