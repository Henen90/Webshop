package ui;

import bo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingcart");

        if(user==null){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try{
            boolean orderSuccess = BOFacade.putOrder(user.getUsername(), shoppingCart, OrderStatus.PACKING);

            if(orderSuccess){
                request.setAttribute("success","Din order har lagts! Tack för ditt köp!");
                session.removeAttribute("shoppingcart");

            } else{
                request.setAttribute("error", "Kunde inte lägga order. Försök igen.");
            }
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } catch (Exception e){

            e.printStackTrace();
            request.setAttribute("error", "Ett fel inträffade");
            request.getRequestDispatcher("shoppingcart.jsp").forward(request,response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }
}
