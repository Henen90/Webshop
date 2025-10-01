package bo;

import db.UserDAO;

import java.sql.SQLException;

public class BOFacade {

    public static UserDTO validateUser(String username, String password) throws SQLException {
        User user = UserDAO.findByUsernameAndPassword(username, password);
        if(user != null){
            return new UserDTO(user.getUserName(), user.geteMail());
        }
        return null;
    }

}
