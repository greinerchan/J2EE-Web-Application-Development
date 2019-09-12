import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Space")
public class Space extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Space Example</title>");
		out.println("</head>");

		out.println("<body>");
		String osName = System.getProperty("os.name");
		out.println("<h2> Server Operating System: " + osName + "</h2>");

		DecimalFormat df = new DecimalFormat("#,###");
		File f;
		if (osName.startsWith("Windows")) {
			f = new File("c:\\");
		} else {
			f = new File("/");
		}
		out.println("<h1>Current free space on the server is " + df.format(f.getFreeSpace()) + " bytes</h1>");
		out.println("</body>");
		out.println("</html>");
	}

}
