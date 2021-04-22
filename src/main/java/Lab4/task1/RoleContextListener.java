package Lab4.task1;

import Lab4.context.EmployeeDTO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RoleContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Integer defaultId = Integer.parseInt(servletContext.getInitParameter("defaultRoleId"));
        String defaultName = servletContext.getInitParameter("defaultRoleName");
        RoleDTO defaultUser = new RoleDTO(defaultId, defaultName);

        Integer adminId = Integer.parseInt(servletContext.getInitParameter("adminRoleId"));
        String adminName = servletContext.getInitParameter("adminRoleName");
        RoleDTO adminUser = new RoleDTO(adminId, adminName);

        servletContext.setAttribute("defaultUser", defaultUser);
        servletContext.setAttribute("adminUser", adminUser);
    }
}
