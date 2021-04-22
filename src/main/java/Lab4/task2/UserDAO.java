package Lab4.task2;

import Lab2.Film;

import java.sql.*;

public class UserDAO {

    public static Connection getDbConnection() throws SQLException {
        String connString = "jdbc:mysql://localhost:3307/rip?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pswd = "root";

        return DriverManager.getConnection(connString, login, pswd);
    }


    public static UserDTO findById(Integer id) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        UserDTO userDTO = null;
        while (resultSet.next()) {
            userDTO = new UserDTO(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)

            );
        }

        connection.close();
        return userDTO;
    }

    public static UserDTO findByLogin(String login) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("SELECT * FROM users WHERE login = ?");
        statement.setString(1, login);

        ResultSet resultSet = statement.executeQuery();

        UserDTO userDTO = null;
        while (resultSet.next()) {
            userDTO = new UserDTO(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)

            );
        }

        return userDTO;
    }

    public static void insert(UserDTO userDTO) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO user(login, password, role_id) VALUES (?, ?, ?)");
        statement.setString(1, userDTO.getLogin());
        statement.setString(2, userDTO.getPassword());
        statement.setInt(3, userDTO.getRole_id());

        System.out.println(statement.execute());
    }

    public static void update(Film film) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("UPDATE film " +
                        "SET title = ?, description = ? " +
                        "WHERE film_id = ?");
        statement.setString(1, film.getTitle());
        statement.setString(2, film.getDescription());
        statement.setInt(3, film.getId());

        System.out.println(statement.execute());
    }

    public static void delete(int id) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("DELETE FROM film WHERE film_id = ?");
        statement.setInt(1, id);

        System.out.println(statement.executeUpdate());
    }

    public static void main(String[] args) throws SQLException {
        UserDTO userDTO = findByLogin("alice@gmail.com");
        System.out.println(userDTO.getRole_id());
        System.out.println(userDTO.getPassword());
    }
}
