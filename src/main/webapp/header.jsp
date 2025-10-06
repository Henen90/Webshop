
<%@ page import="bo.UserDTO" %>
<%
    UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
%>

<div style="background-color:#333; color:white; padding:15px; display:flex; justify-content:space-between; align-items:center;">
    <div>
        <a href="${pageContext.request.contextPath}/" style="color:white; margin:0 15px; text-decoration:none;">Hem</a>
        <a href="shoppingcart.jsp" style="color:white; margin:0 15px; text-decoration:none;">Kundvagn</a>

        <%
            if (loggedInUser != null && "ADMIN".equals(loggedInUser.getRole())) {
        %>
        <a href="admin_users.jsp" style="color:white; margin:0 15px; text-decoration:none;">Användare</a>
        <a href="admin_products.jsp" style="color:white; margin:0 15px; text-decoration:none;">Produkter</a>
        <a href="admin_orders.jsp" style="color:white; margin:0 15px; text-decoration:none;">Beställningar</a>
        <% } %>
    </div>

    <div>
        <%
            if (loggedInUser != null) {
        %>
        Inloggad som <%= loggedInUser.getUsername() %>!
        <a href="logout" style="color:white; margin-left:15px;">Logga ut</a>
        <% } else { %>
        <a href="login.jsp" style="color:white; margin:0 15px;">Logga in</a>
        <a href="register.jsp" style="color:white; margin:0 15px;">Registrera</a>
        <% } %>
    </div>
</div>