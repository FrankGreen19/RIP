package com.example.servlets1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/iinfo")
public class IncludeInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("TestServlet says hi<br/>" +
                "And waiting for '?action=include' or '?action=forward' parameter input ...<br><br>");
        out.println("<form method=GET action=iinfo>");
            out.println("<input type=\"submit\" name=\"forward\" value=\"forward\">");
            out.println("<input type=\"submit\" name=\"include\" value=\"include\">");
        out.println("</form>");

        String include = request.getParameter("include");
        String forward = request.getParameter("forward");

        RequestDispatcher rd = request.getRequestDispatcher("formgetpost");

        if (include != null) {
            rd.include(request, response);
        } else if (forward != null) {
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
