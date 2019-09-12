import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
		out.println("        <title>Hello World!</title>");
		out.println("    </head>");
		out.println("    <body>");
		out.println("        <h1>Hello World!</h1>");
		out.println("    </body>");
		out.println("</html>");
	}
}
