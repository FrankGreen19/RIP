package com.example.servlets1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loop-servlet")
public class LoopServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String color = "blue";
        int number = 3;
        try {
            number = Integer.parseInt(request.getParameter("number"));
            color = String.valueOf(request.getParameter("color"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        PrintWriter out = response.getWriter();
        for (int i = 0; i < number; i++) {
            System.out.println(color);
            out.println("<!DOCTYPE html><html>" +
                    "<head>" +
                        "<title>Page</title>" +
                    "</head><body>");
            out.println("<p style=\"color: " + color + "\">" + "Hello, world!</p>");
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
