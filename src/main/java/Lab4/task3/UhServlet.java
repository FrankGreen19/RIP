package Lab4.task3;

import Lab4.task2.UserDTO;
import Lab4.wrapers.MyHttpRequestWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uhblya")
public class UhServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = "alice@gmail.com";
        login = request.getParameter("login");

//        request.setAttribute("login", login);
        UserHttpRequestWrapper wrapper = new UserHttpRequestWrapper(request);
        PrintWriter out = response.getWriter();
        UserDTO user = (UserDTO) wrapper.getAttribute(login);
        out.println("UserRole = " + user.getRole_id());
    }
}
