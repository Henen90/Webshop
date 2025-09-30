package db;

public class UserDAO extends bo.User{

    protected UserDAO(int id, String firstName, String lastName, String userName, String passWord, String eMail) {
        super(id, firstName, lastName, userName, passWord, eMail);
    }

    public static boolean validateUser(String userName, String password){
        return true;
    }

    public static boolean removeUser(int id){
        return false;
    }
}
