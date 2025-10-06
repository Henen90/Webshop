<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="product-container">

    <c:if test="${not empty products}">

        <c:forEach items="${products}" var="product">
            <div class="product">
                <h3>${product.name}</h3>
                <p>${product.descr}</p>
                <p>Price: ${product.price} kr</p>
                <c:choose>
                    <c:when test="${product.stock == 0}">
                        <p style="color:red;">Varan finns ej i lager</p>
                    </c:when>
                    <c:otherwise>
                        <p style="color:green;">Varan finns i lager (${product.stock} st)</p>
                    </c:otherwise>
                </c:choose>

                <form action="shoppingcart" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button type="submit">Lägg i kundvagn</button>
                </form>
            </div>
        </c:forEach>

    </c:if>

    <c:if test="${empty products}">
        <p>No products were found.</p>
    </c:if>

</div>