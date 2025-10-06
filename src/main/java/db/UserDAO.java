package db;

import bo.Role;
import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO extends bo.User {

    protected UserDAO(int id, String firstName, String lastName, String userName, String passWord, String eMail, String role) {
        super(id, firstName, lastName, userName, passWord, eMail, Role.valueOf(role));
    }


    public static UserDAO findByUsernameAndPassword(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username=? AND password=?;";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println("result is: " + rs.toString());
            if (rs.next()) {
                return new UserDAO(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getString("ROLE")
                );
            }
        }
        return null;
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, firstName, lastName, userName, passWord, eMail, role FROM USERS";

        try (Connection conn = DBManager.getConnection(); // Assuming you have a DatabaseConnector utility
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // IMPORTANT: The User constructor must be updated to accept the role.
                User user = new UserDAO(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("userName"),
                        rs.getString("passWord"), // NOTE: Consider if you truly need to retrieve the password hash here
                        rs.getString("eMail"),
                        rs.getString("ROLE")
                );

                users.add(user);
            }
        }
        return users;
    }

    public static UserDAO findByUsername(String userName) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username=?;";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            System.out.println("result is: " + rs.toString());
            if (rs.next()) {
                return new UserDAO(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getString("ROLE")
                );
            }
        }
        return null;
    }

    public static boolean registerUser(String firstName, String lastName, String userName, String passWord, String eMail, String role) throws SQLException {
        String sql = "INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (?, ?, ?, ?, ?, ?)";
        //List<User> users = findAllUsers();

        //for(int i = 0; i < users.size(); i++){
        //    if(Objects.equals(users.get(i).getUserName(), userName) || Objects.equals(users.get(i).geteMail(), eMail)) return false;
        //}

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, userName);
            stmt.setString(4, passWord);
            stmt.setString(5, eMail);
            stmt.setString(6, role);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Unique index") || e.getSQLState().equals("23505")) {
                return false;
            } else {
                throw e;
            }
        }
    }

    private static List<User> findAllUsers() throws SQLException {
        String sql = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();

        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new UserDAO(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getString("ROLE")
                ));
            }
        }
        return users;
    }


    public static boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public static boolean changeUserRole(int id, String newRole) throws SQLException {
        String sql = "UPDATE USERS SET ROLE = ? WHERE ID = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // The role is stored as a string in the DB (e.g., "ADMIN" or "USER")
            stmt.setString(1, newRole);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

}
