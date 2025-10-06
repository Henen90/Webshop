<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Kundvagn</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2 style="text-align:center;">Din kundvagn</h2>

<table>
    <tr>
        <th>Produkt</th>
        <th>Pris</th>
    </tr>

    <c:set var="totalSum" value="0" />

    <c:forEach items="${sessionScope.shoppingcart.products}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.price} kr</td>
        </tr>
        <c:set var="totalSum" value="${totalSum + item.price}" />
    </c:forEach>

    <tr>
        <td><b>Summa</b></td>
        <td><b>${totalSum} kr</b></td>
    </tr>
</table>

<div class="order-button-container" style="text-align:center; margin-top: 20px;">
    <form action="checkout.jsp">
        <button type="submit" class="order-button">Slutför köp</button>
    </form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>