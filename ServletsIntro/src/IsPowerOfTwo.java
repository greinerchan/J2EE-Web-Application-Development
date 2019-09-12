import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IsPowerOfTwo")
public class IsPowerOfTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		outputHtml(response, null, null);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String numStr = request.getParameter("num");
		
		if (numStr == null) {
			outputHtml(response, "deeppink", "The number parameter is missing!");
			return;
		}
		
		try {
			long num = Long.parseLong(numStr);
			if (isPowerOfTwo(num)) {
				outputHtml(response, "green", num + " is a power of two");
				return;
			} 

			outputHtml(response, "firebrick", num + " is NOT a power of two");

		} catch (NumberFormatException e) {
			outputHtml(response, "deeppink", "&quot;" + numStr + "&quot; is not a number!");
		}
	}

	private boolean isPowerOfTwo(long x) {
		System.out.println("isPowerOfTwo(" + x + ") called.");
		return ((x & (x - 1)) == 0 && x > 0);
	}
	
	private void outputHtml(HttpServletResponse response, String color, String message)
			throws IOException {
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
		out.println("<title>IsPowerOfTwo</title>");
		out.println("</head>");
		out.println("<body>");
		
		if (message != null) {
			out.println("<h2 style=\"color:" + color + ";\">");
			out.println(message);
			out.println("</h2>");
		}

		out.println("<div style=\"font-size:24px\">");
		out.println("  <form action=\"IsPowerOfTwo\" method=\"POST\">");
		out.println("    Number Please: <input type=\"text\" size=\"20\" name=\"num\" autofocus>");
		out.println("    <input type=\"submit\" value=\"Is It a Power of Two?\">");
		out.println("  </form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
