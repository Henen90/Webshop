package ui;

import bo.BOFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String eMail = request.getParameter("email");
        String role = "CUSTOMER";

        try{
            boolean regSuccess = BOFacade.registerUser(firstName,lastName,userName,passWord,eMail,role);

            if(regSuccess){
                response.sendRedirect("login.jsp?registered=true");
            } else {
                request.setAttribute("error", "Kunde inte skapa användare. Försök igen.");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ett fel inträffade");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }


    }
}
