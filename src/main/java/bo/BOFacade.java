package bo;

import db.UserDAO;

import java.sql.SQLException;

public class BOFacade {

    public boolean validateUser(String username, String password) throws SQLException {
        if(UserDAO.findByUsernameAndPassword(username, password)) return true;
        return false;
    }

}
