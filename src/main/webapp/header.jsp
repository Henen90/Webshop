<%@ page import="bo.UserDTO" %>
<%
    UserDTO loggedInUser = (UserDTO) session.getAttribute("user");
%>

<div style="background-color:#333; color:white; padding:15px; display:flex; justify-content:space-between; align-items:center;">
    <div>
        <a href="${pageContext.request.contextPath}/" style="color:white; margin:0 15px; text-decoration:none;">Hem</a>
        <a href="${pageContext.request.contextPath}/shoppingcart.jsp" style="color:white; margin:0 15px; text-decoration:none;">Kundvagn</a>

        <%
            if (loggedInUser != null) {
                String role = loggedInUser.getRole();
                if ("ADMIN".equals(role)) {
        %>
        <a href="${pageContext.request.contextPath}/admin/users" style="color:white; margin:0 15px; text-decoration:none;">Användare</a>
        <a href="${pageContext.request.contextPath}/admin/products" style="color:white; margin:0 15px; text-decoration:none;">Produkter</a>
        <a href="${pageContext.request.contextPath}/admin/orders" style="color:white; margin:0 15px; text-decoration:none;">Beställningar</a>
        <%
        } else if ("WAREHOUSE_STAFF".equals(role)) {
        %>
        <a href="${pageContext.request.contextPath}/admin/orders" style="color:white; margin:0 15px; text-decoration:none;">Beställningar</a>
        <%
                }
            }
        %>
    </div>

    <div>
        <%
            if (loggedInUser != null) {
        %>
        Inloggad som <%= loggedInUser.getUsername() %> (Role: <%= loggedInUser.getRole() %>)!
        <a href="${pageContext.request.contextPath}/logout" style="color:white; margin-left:15px;">Logga ut</a>
        <% } else { %>
        <a href="${pageContext.request.contextPath}/login.jsp" style="color:white; margin:0 15px;">Logga in</a>
        <a href="${pageContext.request.contextPath}/register.jsp" style="color:white; margin:0 15px;">Registrera</a>
        <% } %>
    </div>
</div>