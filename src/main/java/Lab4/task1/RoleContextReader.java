package Lab4.task1;

import Lab4.context.EmployeeDTO;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/roleContext")
public class RoleContextReader extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        RoleDTO defaultUser = (RoleDTO) getServletContext().getAttribute("defaultUser");
        RoleDTO adminUser = (RoleDTO) getServletContext().getAttribute("adminUser");
        out.println(defaultUser.getName());
        out.println(adminUser.getName());
    }
}
