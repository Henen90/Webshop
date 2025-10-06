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
    <title>Administration: Produkter</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main class="admin-container">
    <h2>Produktadministration</h2>

    <c:if test="${not empty sessionScope.message}"><p class="message success">${sessionScope.message}<c:remove var="message" scope="session"/></p></c:if>
    <c:if test="${not empty sessionScope.error}"><p class="message error">${sessionScope.error}<c:remove var="error" scope="session"/></p></c:if>

    <div class="form-container">
        <h3>Lägg till ny produkt</h3>
        <form action="${pageContext.request.contextPath}/admin/addProduct" method="post">
            <input type="text" name="name" placeholder="Produktnamn" required>
            <textarea name="descr" placeholder="Beskrivning" required></textarea>
            <input type="text" name="category" placeholder="Kategori" required>
            <input type="number" name="price" placeholder="Pris" step="0.01" min="0" required>
            <input type="number" name="stock" placeholder="Lagersaldo" min="0" required>
            <button type="submit">Lägg till produkt</button>
        </form>
    </div>

    <div class="admin-table-wrapper">
        <h3>Befintliga produkter</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Namn</th>
                <th>Kategori</th>
                <th>Pris</th>
                <th>Lager</th>
                <th>Åtgärder</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allProducts}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.category}</td>
                    <td>${p.price} kr</td>
                    <td>${p.stock}</td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/admin/editProduct?id=${p.id}" class="button edit">Redigera</a>
                        <form action="${pageContext.request.contextPath}/admin/deleteProduct" method="post" onsubmit="return confirm('Är du säker på att du vill ta bort denna produkt?');" class="inline-form">
                            <input type="hidden" name="productId" value="${p.id}">
                            <button type="submit" class="danger">Ta bort</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>