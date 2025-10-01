package db;

import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends bo.User{

    protected UserDAO(int id, String firstName, String lastName, String userName, String passWord, String eMail) {
        super(id, firstName, lastName, userName, passWord, eMail);
    }


    public static User findByUsernameAndPassword(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username=? AND password=?;";
        try(Connection connection = DBManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println("result is: "+rs.toString());
            if(rs.next()){
                return new User(
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

    public static boolean removeUser(int id){
        return false;
    }
}
