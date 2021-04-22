package com.example.servlets1;

import Lab2.FilmsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/GetFilmsFromSAKILADB")
public class GetFilmsFromSAKILADB extends HttpServlet {

    private interface Names {
        String selectFilmsNumber = "selectFilmsNumber";
        String connString = "jdbc:mysql://localhost:3307/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "SELECT film_id, title,description FROM film WHERE film_id<=?";
        String login = "root";
        String pswd = "root";
    }

    final int tableBorderWidth = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(""
                    + "<form action=GetFilmsFromSAKILADB method=GET>"
                    + "Вывести первые"
                    + "<select name=" + Names.selectFilmsNumber + ">"
                    + "<option>5</option><option>10</option><option>20</option><option>30</option><option>50</option><option>1000</option>"
                    + "</select> фильмов"
                    + "<input type=submit name=submitNumber value=OK>"
                    + "</form>");

            try {
                int numberOfFilmsToExtract = Integer.parseInt(request.getParameter(Names.selectFilmsNumber));
                Class.forName("com.mysql.cj.jdbc.Driver");

                try (Connection con = DriverManager.getConnection(Names.connString, Names.login, Names.pswd);
                     PreparedStatement preparedStatement = con.prepareStatement(Names.sqlQuery);
                ) {
                    preparedStatement.setInt(1, numberOfFilmsToExtract);
                    ResultSet filmsResultSet = preparedStatement.executeQuery();

                    out.println("<a href=insert-servlet>Добавить</a>");

                    out.println("<table border=" + tableBorderWidth + " bgcolor=yellow>");
                    while (filmsResultSet.next()) {
                        out.println("<tr>");
                        out.println("<td>");
                        out.println(filmsResultSet.getString("title"));
                        out.println("</td>");
                        out.println("<td>");
                        out.println(filmsResultSet.getString("description"));
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<a href=update-servlet?id=" + filmsResultSet.getString("film_id") + ">Изменить</a>");
                        out.println("</td>");
                        out.println("<td>");
                        out.println("<a href=delete-servlet?id=" + filmsResultSet.getString("film_id") +">Удалить</a>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("<table>");
                    filmsResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (NumberFormatException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            out.println("</body>");
            out.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}