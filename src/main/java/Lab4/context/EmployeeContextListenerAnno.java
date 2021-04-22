package Lab4.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener //за счет аннотации MyServletContextListenerAnno не надо прописывать в web.xml
//делает то же, что и MyServletContextListener, только за счет аннотаций
//т.е. создает нового Employee и передает его как атрибут в контекст
public class EmployeeContextListenerAnno implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String name = servletContext.getInitParameter("secondEmployeesName");
        EmployeeDTO employee = new EmployeeDTO(name);
        servletContext.setAttribute("secondEmployee",employee);
    }

//    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContext sc = sce.getServletContext();
//        sc.removeAttribute("eName");
//        sc.removeAttribute("eName2");
//        System.out.println("Values deleted from context.");
//    }
}
