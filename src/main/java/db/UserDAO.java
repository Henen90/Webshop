package db;

import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO extends bo.User{

    protected UserDAO(int id, String firstName, String lastName, String userName, String passWord, String eMail) {
        super(id, firstName, lastName, userName, passWord, eMail);
    }


    public static UserDAO findByUsernameAndPassword(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username=? AND password=?;";
        try(Connection connection = DBManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println("result is: "+rs.toString());
            if(rs.next()){
                return new UserDAO(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL")
                );
            }
        }
        return null;
    }

    public static boolean registerUser(String firstName, String lastName, String userName, String passWord, String eMail) throws SQLException{
        String sql = "INSERT INTO USERS (FIRSTNAME, LASTNAME, USERNAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?, ?)";
        //List<User> users = findAllUsers();

        //for(int i = 0; i < users.size(); i++){
        //    if(Objects.equals(users.get(i).getUserName(), userName) || Objects.equals(users.get(i).geteMail(), eMail)) return false;
        //}

        try(Connection connection = DBManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, userName);
            stmt.setString(4, passWord);
            stmt.setString(5, eMail);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch(SQLException e){
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

        try(Connection connection = DBManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                users.add(new UserDAO(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"))
                );
            }
        }
        return users;
    }

    public static boolean removeUser(int id){
        return false;
    }
}
