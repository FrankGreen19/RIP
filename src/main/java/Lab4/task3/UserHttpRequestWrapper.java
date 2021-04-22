package Lab4.task3;

import Lab4.task2.UserDAO;
import Lab4.task2.UserDTO;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import java.sql.SQLException;

public class UserHttpRequestWrapper extends ServletRequestWrapper {

    public UserHttpRequestWrapper(ServletRequest request) {
        super(request);
    }

    public static void main(String[] args) throws SQLException {
        UserDTO userDTO = UserDAO.findByLogin("alice@gmail.com");
        System.out.println(userDTO.getRole_id());
        System.out.println(userDTO.getPassword());

    }

    @Override
    public Object getAttribute(String login) {
        try {
            return UserDAO.findByLogin(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void setAttribute(String login, Object o) {
        super.setAttribute(login, o);
    }
}

class Main {
    public static void main(String[] args) throws SQLException {
        UserDTO userDTO = UserDAO.findByLogin("alice@gmail.com");
        System.out.println(userDTO.getRole_id());
        System.out.println(userDTO.getPassword());
    }
}
