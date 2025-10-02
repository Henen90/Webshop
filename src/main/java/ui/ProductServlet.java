package ui;

import bo.BOFacade;
import bo.ProductDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", value = "")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ProductDTO> products = BOFacade.getAllProducts();
            request.setAttribute("products", products);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Could not load products. Please try again later.");
            e.printStackTrace();
        }

        // UNCOMMENT these lines and forward to index.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
