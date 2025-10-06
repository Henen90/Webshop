package ui;

import bo.BOFacade;
import bo.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Map the servlet to handle all admin actions
@WebServlet(urlPatterns = {"/admin/users", "/admin/deleteUser", "/admin/changeRole"})
public class AdminServlet extends HttpServlet {

    // Helper method to redirect to the user listing page
    private void redirectToUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");

        // 1. Security Check (already handled in JSP, but good to have in servlet too)
        if (loggedInUser == null || !"ADMIN".equals(loggedInUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Ensure this method is called only when displaying the list
        if (request.getServletPath().equals("/admin/users")) {
            try {
                // 2. Fetch all users
                List<UserDTO> allUsers = BOFacade.getAllUsers();
                request.setAttribute("allUsers", allUsers);

                // 3. Forward to the display page
                request.getRequestDispatcher("/admin_users.jsp").forward(request, response);

            } catch (SQLException e) {
                request.setAttribute("error", "Kunde inte hämta användardata.");
                request.getRequestDispatcher("/admin_users.jsp").forward(request, response);
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");

        // 1. Security Check
        if (loggedInUser == null || !"ADMIN".equals(loggedInUser.getRole())) {
            redirectToUsers(request, response);
            return;
        }

        String actionPath = request.getServletPath();
        String userIdParam = request.getParameter("userId");

        if (userIdParam == null) {
            session.setAttribute("error", "Ogiltig användar-ID.");
            redirectToUsers(request, response);
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);

            if (actionPath.equals("/admin/deleteUser")) {
                if (BOFacade.deleteUser(userId)) {
                    session.setAttribute("message", "Användare med ID " + userId + " borttagen.");
                } else {
                    session.setAttribute("error", "Kunde inte ta bort användare (ID: " + userId + ").");
                }

            } else if (actionPath.equals("/admin/changeRole")) {
                String newRole = request.getParameter("newRole");
                if (BOFacade.changeUserRole(userId, newRole)) {
                    session.setAttribute("message", "Användarroll för ID " + userId + " ändrad till " + newRole + ".");
                } else {
                    session.setAttribute("error", "Kunde inte ändra roll för användare (ID: " + userId + ").");
                }
            }

        } catch (NumberFormatException e) {
            session.setAttribute("error", "Ogiltigt ID-format.");
        } catch (SQLException e) {
            session.setAttribute("error", "Databasfel vid åtgärd.");
            e.printStackTrace();
        }

        // Redirect back to the GET method to show the updated list and clear message/error attributes
        redirectToUsers(request, response);
    }
}