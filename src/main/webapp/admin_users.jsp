<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="bo.UserDTO" %>

<%
    UserDTO user = (UserDTO) session.getAttribute("user");

    if (user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>

<html>
<head>
    <title>Administration: Användare</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        h2 { text-align: center; color: #333; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        form { display: inline; margin-right: 10px; }
        .action-form { margin: 15px auto; width: 80%; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<h2>Användaradministration</h2>

<c:if test="${not empty message}">
    <p style="color:green; text-align:center;">${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red; text-align:center;">${error}</p>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Användarnamn</th>
        <th>Roll</th>
        <th>Åtgärder</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${allUsers}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.role}</td>
            <td>
                <form action="admin/deleteUser" method="post" onsubmit="return confirm('Är du säker på att du vill ta bort användaren ${u.username}?');">
                    <input type="hidden" name="userId" value="${u.id}">
                    <button type="submit" style="color:red; border:none; background:none; cursor:pointer;">Ta bort</button>
                </form>

                <form action="admin/changeRole" method="post">
                    <input type="hidden" name="userId" value="${u.id}">
                    <select name="newRole">
                        <option value="USER" <c:if test="${u.role eq 'USER'}">selected</c:if>>USER</option>
                        <option value="ADMIN" <c:if test="${u.role eq 'ADMIN'}">selected</c:if>>ADMIN</option>
                    </select>
                    <button type="submit">Ändra Roll</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>

<div class="action-form">
    <h3>Lägg till ny användare (via Register.jsp)</h3>
    <p>För att lägga till en ny användare med ett lösenord, <a href="register.jsp">använd registreringssidan</a>.</p>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>