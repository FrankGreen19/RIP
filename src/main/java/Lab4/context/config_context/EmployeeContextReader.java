package Lab4.context.config_context;

import Lab4.context.EmployeeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//считывает из контекста объект Employee и распечатывает его параметры
@WebServlet("/lisna1")
public class EmployeeContextReader extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        EmployeeDTO employee = (EmployeeDTO) getServletContext().getAttribute("firstEmployee");
        EmployeeDTO employee2 = (EmployeeDTO) getServletContext().getAttribute("secondEmployee");
        out.println(employee.getName() + "\n");
        out.println(employee2.getName());
    }
}
