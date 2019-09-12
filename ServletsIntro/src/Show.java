
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
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
		out.println("        <title>Show Information in Request</title>");
		out.println("    </head>");
		out.println("    <body>");
		out.println("        <table>");
		out.println(tableRow("Query String:", request.getQueryString()));
		out.println(tableRow("Remote Host Address:", request.getRemoteAddr()));
		out.println(tableRow("Remote Host Name:", request.getRemoteHost()));
		out.println(tableRow("Remote Port:", String.valueOf(request.getRemotePort())));
		out.println(tableRow("Request Method:", request.getMethod()));
		out.println(tableRow("Server Name:", request.getServerName()));
		out.println(tableRow("Server Port:", String.valueOf(request.getServerPort())));
		out.println(tableRow("Servlet Path:", request.getServletPath()));
		out.println("        </table>");

		out.println("        <br>");
		out.println("        <hr>");
		out.println("        <h2>Headers</h2>");
		out.println("        <hr>");
		out.println("        <table>");

		Enumeration<String> headerEnum = request.getHeaderNames();

		while (headerEnum.hasMoreElements()) {
			String name = headerEnum.nextElement();
			out.println(tableRow(name+':', request.getHeader(name)));
		}
		out.println("        </table>");

		if (request.getMethod().equals("POST")) {
			outputBody(request, out);
		}
		
		out.println("    </body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	private String tableRow(String description, String value) {
		StringBuffer b = new StringBuffer();
		b.append("<tr><td><h3>");
		b.append(description);
		b.append("</h3></td><td><h3>");
		b.append(value);
		b.append("</h3></td></tr>\n");
		return b.toString();
	}
	
	private void outputBody(HttpServletRequest request, PrintWriter out) throws IOException {
		InputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		boolean allPrintable = true;
		int x = is.read();
		while (x != -1 && baos.size() < 1000*1000) {
			baos.write(x);
			if (x < 0x20 || x > 0x7e) {
				allPrintable = false;
			}
			x = is.read();
		}
		
		out.println("<hr>");
		out.println("<h3>Content Length = " + request.getContentLength() + "</h3>");
		out.println("<h3>Body contained " + baos.size() + " bytes</h3>");
		out.println("<h3>All bytes printable: " + allPrintable + "</h3>");
		out.println("<hr>");
		
		out.println("<div style=\"font-size:x-large; font-family:monospace;\">");
		if (allPrintable) {
			out.println(baos);
		} else {
			for (byte b : baos.toByteArray()) {
				out.printf("%02x", b);
			}
			out.println();
		}
		out.println("</div>");
		out.println("<hr>");
	}
}
