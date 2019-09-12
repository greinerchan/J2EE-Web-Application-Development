import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EntryDAO;
import databeans.Entry;

@WebServlet("/namesearch")
public class NameSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private EntryDAO dao = new EntryDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String start = request.getParameter("start");

		if (start == null) {
			request.setAttribute("error", "Invalid parameter: start==null");
			RequestDispatcher d = request.getRequestDispatcher("AddrBookError.jsp");
			d.forward(request, response);
			return;
		}

		Entry[] list = dao.lookupByStartOfLastName(start);

		if (list.length == 0) {
			request.setAttribute("error", "No entries for " + start);
			RequestDispatcher d = request.getRequestDispatcher("AddrBookError.jsp");
			d.forward(request, response);
			return;
		}

		if (list.length > 1) {
			request.setAttribute("list", list);
			RequestDispatcher d = request.getRequestDispatcher("AddrBookList.jsp");
			d.forward(request, response);
			return;
		}

		request.setAttribute("entry", list[0]);
		RequestDispatcher d = request.getRequestDispatcher("AddrBookEntry.jsp");
		d.forward(request, response);
	}
}
