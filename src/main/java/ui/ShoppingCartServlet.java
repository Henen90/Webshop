package ui;

import bo.BOFacade;
import bo.ProductDTO;
import bo.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ShoppingCartServlet", value ="/shoppingcart")
public class ShoppingCartServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingcart", shoppingCart);
        }

        try {
            int id = Integer.parseInt(request.getParameter("productId"));
            ProductDTO product = BOFacade.getProductById(id);

            if (product != null) {
                if(product.getStock()>0) {
                    shoppingCart.addToCart(product);
                    request.setAttribute("success", "Produkten '" + product.getName() + "' har lagts till i kundvagnen!");
                }else{
                    request.setAttribute("error","Produkten finns tyvärr inte i lager.");
                }
            }
            //response.sendRedirect(request.getContextPath());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("shoppingcart.jsp");
    }

}