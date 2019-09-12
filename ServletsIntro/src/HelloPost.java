import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloPost")
public class HelloPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("  <head>");
        out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>HelloPost</title>");
		out.println("    <style> form { font-size:xx-large } </style>");
		out.println("    <style> input { font-size:large } </style>");
		out.println("  </head>");
		out.println("  <body>");
		out.println("    <form action=\"HelloPost\" method=\"POST\">");
		out.println("      Name: <input type=\"text\" size=\"40\" name=\"name\">");
		out.println("      <input type=\"submit\">");
		out.println("    </form>");
		out.println("  </body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html>");
		out.println("<html>");
		out.println("  <head>");
		out.println("    <title>HelloPost</title>");
		out.println("    <style> form { font-size:xx-large } </style>");
		out.println("    <style> input { font-size:large } </style>");
		out.println("  </head>");
		out.println("  <body>");
		out.println("    <h1>Hello, " + name + "!</h1>");
		out.println("  </body>");
		out.println("</html>");
	}
}
