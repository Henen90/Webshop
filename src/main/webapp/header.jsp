<div style="background-color:#333; color:white; padding:15px; display:flex; justify-content:space-between; align-items:center;">
    <div>
        <a href="index.jsp" style="color:white; margin:0 15px; text-decoration:none;">Hem</a>
        <a href="shoppingCart.jsp" style="color:white; margin:0 15px; text-decoration:none;">Kundvagn</a>
    </div>
    <div>
        <%
            Object user = session.getAttribute("user");
            if (user != null) {
        %>
        Inloggad som <%= ((bo.UserDTO) user).getUsername() %>!
        <a href="logout" style="color:white; margin-left:15px;">Logga ut</a>
        <% } else { %>
        <a href="login.jsp" style="color:white; margin:0 15px;">Logga in</a>
        <a href="register.jsp" style="color:white; margin:0 15px;">Registrera</a>
        <% } %>
    </div>
</div>
