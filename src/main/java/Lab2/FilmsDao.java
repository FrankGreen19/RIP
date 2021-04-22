package Lab2;

import com.example.servlets1.GetFilmsFromSAKILADB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmsDao {

    public static Connection getDbConnection() throws SQLException {
        String selectFilmsNumber = "selectFilmsNumber";
        String connString = "jdbc:mysql://localhost:3307/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String sqlQuery = "SELECT title,description FROM film WHERE film_id<=?";
        String login = "root";
        String pswd = "root";

        return DriverManager.getConnection(connString, login, pswd);
    }

    public static void insert(Film film) throws SQLException {
        Connection connection = getDbConnection();

        PreparedStatement statement =
                connection.prepareStatement("INSERT INTO film(title, description, language_id) VALUES (?, ?, 1)");
        statement.setString(1, film.getTitle());
        statement.setString(2, film.getDescription());

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
        Film film = new Film(
                1001,
                "Project X",
                "Cool2"
        );

        delete(1004);
    }
}
