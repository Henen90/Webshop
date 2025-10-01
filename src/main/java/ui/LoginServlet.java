package ui;

import bo.BOFacade;
import db.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private BOFacade userFacade = new BOFacade();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginSuccess = false;
        try {
            loginSuccess = userFacade.validateUser(username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (loginSuccess) {
            response.sendRedirect("index.jsp");
        }
        else {
            try {
                request.setAttribute("error", "Invalid details");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            catch (Exception e) {
                System.out.println("user not found");
            }
        }
    }

}
