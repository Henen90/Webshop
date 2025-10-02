<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Kundvagn</title>
    <%-- Din CSS här --%>
</head>
<body>
<%@ include file="header.jsp" %>
<h2 style="text-align:center;">Din kundvagn</h2>

<table>
    <tr>
        <th>Produkt</th>
        <th>Pris</th>
    </tr>

    <%-- Sätt en variabel för totalsumman --%>
    <c:set var="totalSum" value="0" />

    <%-- Loopa igenom produkterna i kundvagnen från sessionen --%>
    <c:forEach items="${sessionScope.shoppingcart.products}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.price} kr</td>
        </tr>
        <%-- Addera produktens pris till totalsumman --%>
        <c:set var="totalSum" value="${totalSum + item.price}" />
    </c:forEach>

    <%-- Visa totalsumman --%>
    <tr>
        <td><b>Summa</b></td>
        <td><b>${totalSum} kr</b></td>
    </tr>
</table>

<%-- ... knappar etc. ... --%>
<%@ include file="footer.jsp" %>
</body>
</html>