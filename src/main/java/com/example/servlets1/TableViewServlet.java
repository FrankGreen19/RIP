package com.example.servlets1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/table-view-servlet")
public class TableViewServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger( TableViewServlet.class.getName() );

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            try {
                int row = Integer.parseInt(request.getParameter("rows"));
                int col = Integer.parseInt(request.getParameter("columns"));
                out.println("" +
                        "Table: строк " + row + " столбцов " + col + getServletInfo().toString() + "\n" +
                        "<font name=tahoma size=6 bgcolor=White>" +
                        "<table border=2 bgcolor=yellow>" +
                        "");
                for (int i = 0; i < row; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < col; j++) {
                        out.println("" +
                                "<td>i*j=" + i * j + "</td>" +
                                "");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (NumberFormatException e) {
                LOGGER.log(Level.SEVERE,"Задать числа в качестве строк и столбцов:", e);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
