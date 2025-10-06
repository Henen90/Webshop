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
    <title>Redigera Produkt</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main class="admin-container">
    <h2>Redigera Produkt: ${product.name}</h2>

    <c:if test="${not empty product}">
        <div class="form-container">
            <form action="${pageContext.request.contextPath}/admin/saveProduct" method="post">
                <input type="hidden" name="id" value="${product.id}">

                <label for="name">Namn:</label>
                <input type="text" id="name" name="name" value="${product.name}" required>

                <label for="descr">Beskrivning:</label>
                <textarea id="descr" name="descr" required>${product.descr}</textarea>

                <label for="category">Kategori:</label>
                <input type="text" id="category" name="category" value="${product.category}" required>

                <label for="price">Pris:</label>
                <input type="number" id="price" name="price" value="${product.price}" step="0.01" min="0" required>

                <label for="stock">Lagersaldo:</label>
                <input type="number" id="stock" name="stock" value="${product.stock}" min="0" required>

                <button type="submit">Spara ändringar</button>
                <a href="${pageContext.request.contextPath}/admin/products" class="button secondary">Avbryt</a>
            </form>
        </div>
    </c:if>
    <c:if test="${empty product}">
        <p class="message error">Produkten kunde inte hittas.</p>
    </c:if>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>