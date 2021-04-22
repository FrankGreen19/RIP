package Lab3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Lab3.FormServlet.Helper.HEIGHT;


@WebServlet(name="/FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {

    public interface Helper {
        String HEIGHT = "height";
        String WEIGHT = "weight";
        //расчет индекса массы тела
        static Double calculateBMI(Double weight, Double height) {
            return weight / (height * height);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String height=request.getParameter(HEIGHT);
        String weight=request.getParameter(Helper.WEIGHT);
        try {
            double bmi = Helper.calculateBMI(Double.parseDouble(weight), Double.parseDouble(height));
            //в отличие от задания аттрибутов через servletContext.setAttribute("emp",employee);
            //это аттрибут не для контекста сервлетов проекта, а только для данного request
            //значит его можно передать дальше по цепочке через RequestDispatcher
            request.setAttribute("bmi", bmi);
            response.setHeader("Test", "Success");
            response.setHeader("BMI", String.valueOf(bmi));

            //за счет использования RequestDispatcher request вместе со своими переменными передается далее в bmi.jsp
            //при этом! в адресной строке остается /calculateServlet, хотя реально все перенаправлено в bmi.jsp
            //и клиент видит bmi.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("bmi.jsp");
            dispatcher.forward(request, response);
            /*
            Если же использовать response.sendRedirect("bmi.jsp") то это просто вызовет bmi.jsp и не получится
            передать рассчитанное значение bmi.

             перенаправление vs переадресация
             dispatcher.forward - перенаправление ТЕКУЩЕГО запроса далее по адресу диспетчера с сохранением текущего адреса;
             response.sendRedirect - просто переход на другой ресурс;
             */
        } catch (Exception e) {
            //а здесь просто открывается новый ресурс error.jsp с новым своим request-ом, а не унаследованным от bmi.jsp
            response.setStatus(303);
        }
    }

}