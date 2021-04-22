package Lab2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<form method=post action=insert-servlet>");
        out.println("<input type=text name=title placeholder=\"title\">");
        out.println("<input type=text name=description placeholder=\"description\">");
        out.println("<input type=submit name=accept value=Send>");
        out.println("</form>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Film film = new Film(
                title,
                description
        );

        try {
            FilmsDao.insert(film);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        RequestDispatcher rd = request.getRequestDispatcher("GetFilmsFromSAKILADB");
//        rd.forward(request, response);

    }
}
