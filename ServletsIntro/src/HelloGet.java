import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/HelloGet", "/hello.html" })
public class HelloGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
		out.println("<title>HelloGet</title>");
		out.println("<style> form { font-size:xx-large } </style>");
		out.println("<style> input { font-size:large } </style>");
		out.println("</head>");
		out.println("<body>");
		if (name == null) {
			out.println("<form action=\"HelloGet\" method=\"GET\">");
			out.println("  Name: <input type=\"text\" size=\"40\" name=\"name\">");
			out.println("  <input type=\"submit\">");
			out.println("</form>");
		} else {
			out.println("<h1>Hello, " + name + "!</h1>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
