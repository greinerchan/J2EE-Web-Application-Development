import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EntryDAO;
import databeans.Entry;

@WebServlet("/idsearch")
public class IdSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EntryDAO dao = new EntryDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String idStr = request.getParameter("id");

        if (idStr == null) {
            request.setAttribute("error", "Invalid parameter: idStr == null");
            RequestDispatcher d = request
                    .getRequestDispatcher("AddrBookError.jsp");
            d.forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error",
                    "Invalid parameter: idStr is not an int");
            RequestDispatcher d = request
                    .getRequestDispatcher("AddrBookError.jsp");
            d.forward(request, response);
            return;
        }

        Entry e = dao.read(id);

        if (e == null) {
            request.setAttribute("error", "No entry for id " + id);
            RequestDispatcher d = request
                    .getRequestDispatcher("AddrBookError.jsp");
            d.forward(request, response);
            return;
        }

        request.setAttribute("entry", e);
        RequestDispatcher d = request
                .getRequestDispatcher("AddrBookEntry.jsp");
        d.forward(request, response);
    }
}
