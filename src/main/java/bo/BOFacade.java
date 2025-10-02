package bo;

import db.ProductDAO;
import db.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BOFacade {

    public static UserDTO validateUser(String username, String password) throws SQLException {
        User user = UserDAO.findByUsernameAndPassword(username, password);
        if(user != null){
            return new UserDTO(user.getUserName(), user.geteMail());
        }
        return null;
    }

    public static List<ProductDTO> getAllProducts() throws SQLException {
        List<Product> products = ProductDAO.getAllProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(int i = 0; i < products.size(); i++){
            productDTOS.add(new ProductDTO(products.get(i).getName(),products.get(i).getDescr(),products.get(i).getPrice(),products.get(i).getCategory()));
        }
        return productDTOS;
    }

    public static boolean registerUser(String firstName, String lastName, String userName, String passWord, String eMail) throws SQLException {
        return UserDAO.registerUser(firstName,lastName,userName,passWord,eMail);
    }

    public static List<ProductDTO> getProductsByName(){



        return null;
    }

    public static List<ProductDTO> getProductsByCategory(){



        return null;
    }

}
