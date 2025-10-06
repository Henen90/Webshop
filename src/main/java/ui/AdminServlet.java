package ui;

import bo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {
        "/admin/users", "/admin/products", "/admin/orders",
        "/admin/deleteUser", "/admin/changeRole",
        "/admin/addProduct", "/admin/deleteProduct", "/admin/editProduct", "/admin/saveProduct",
        "/admin/updateOrderStatus"
})
public class AdminServlet extends HttpServlet {

    private void redirectToUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
    private void redirectToProducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }
    private void redirectToOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserDTO loggedInUser = (session != null) ? (UserDTO) session.getAttribute("user") : null;
        String path = request.getServletPath();

        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String userRole = loggedInUser.getRole();

        try {
            if ("/admin/orders".equals(path)) {
                if (!"ADMIN".equals(userRole) && !"WAREHOUSE_STAFF".equals(userRole)) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
                request.setAttribute("allOrders", BOFacade.getAllOrders());
                request.getRequestDispatcher("/admin_orders.jsp").forward(request, response);
            } else {
                if (!"ADMIN".equals(userRole)) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
                if ("/admin/users".equals(path)) {
                    request.setAttribute("allUsers", BOFacade.getAllUsers());
                    request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
                } else if ("/admin/products".equals(path)) {
                    request.setAttribute("allProducts", BOFacade.getAllProducts());
                    request.getRequestDispatcher("/admin_products.jsp").forward(request, response);
                } else if ("/admin/editProduct".equals(path)) {
                    int productId = Integer.parseInt(request.getParameter("id"));
                    ProductDTO product = BOFacade.getProductById(productId);
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/edit_product.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            session.setAttribute("error", "Kunde inte hämta data: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserDTO loggedInUser = (session != null) ? (UserDTO) session.getAttribute("user") : null;
        String actionPath = request.getServletPath();

        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String userRole = loggedInUser.getRole();

        try {
            if ("/admin/updateOrderStatus".equals(actionPath)) {
                if (!"ADMIN".equals(userRole) && !"WAREHOUSE_STAFF".equals(userRole)) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String newStatus = request.getParameter("newStatus");

                if (BOFacade.updateOrderStatus(orderId, newStatus)) {
                    session.setAttribute("message", "Beställning #" + orderId + " uppdaterad till: " + newStatus);
                } else {
                    session.setAttribute("error", "Kunde inte uppdatera status för beställning #" + orderId + ".");
                }
                redirectToOrders(request, response);
                return;
            }

            if (!"ADMIN".equals(userRole)) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }

            if ("/admin/deleteUser".equals(actionPath) || "/admin/changeRole".equals(actionPath)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                if ("/admin/deleteUser".equals(actionPath)) {
                    BOFacade.deleteUser(userId);
                } else {
                    String newRole = request.getParameter("newRole");
                    BOFacade.changeUserRole(userId, newRole);
                }
                redirectToUsers(request, response);
            } else if ("/admin/addProduct".equals(actionPath)) {
                BOFacade.addProduct(request.getParameter("name"), request.getParameter("descr"), request.getParameter("category"), Float.parseFloat(request.getParameter("price")), Integer.parseInt(request.getParameter("stock")));
                redirectToProducts(request, response);
            } else if ("/admin/deleteProduct".equals(actionPath)) {
                BOFacade.removeProduct(Integer.parseInt(request.getParameter("productId")));
                redirectToProducts(request, response);
            } else if ("/admin/saveProduct".equals(actionPath)) {
                BOFacade.updateProduct(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("descr"), request.getParameter("category"), Float.parseFloat(request.getParameter("price")), Integer.parseInt(request.getParameter("stock")));
                redirectToProducts(request, response);
            }

        } catch (NumberFormatException e) {
            session.setAttribute("error", "Ogiltigt numeriskt format i formuläret.");
            response.sendRedirect(request.getContextPath() + "/");
        } catch (SQLException e) {
            session.setAttribute("error", "Databasfel vid åtgärd.");
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}