package Lab4.wrapers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServletWithWrappedRequest")
public class MyServletWithWrappedRequest extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mydata", "Data1");
        MyHttpRequestWrapper wrapper = new MyHttpRequestWrapper(request);
        PrintWriter out = response.getWriter();
        out.println("MyWrapper = " + wrapper.getAttribute("name"));
    }
}