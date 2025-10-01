package ui;

import bo.BOFacade;
import bo.UserDTO;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        try {
            UserDTO userDTO = BOFacade.validateUser(username, password);
            if (userDTO != null) {
                request.getSession().setAttribute("user", userDTO);
                response.sendRedirect("index.jsp");
            }
            else {
                request.setAttribute("error", "Invalid details");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                System.out.println("user not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
