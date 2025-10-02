<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<h1>Our Products</h1>

<div class="product-container">

    <%-- This checks if the products list from the servlet is not empty --%>
    <c:if test="${not empty products}">

        <%-- THIS IS THE DYNAMIC LOOP --%>
        <%-- It will repeat the <div> block for every "product" in the "products" list --%>
        <c:forEach items="${products}" var="product">
            <div class="product">
                <h3>${product.name}</h3>
                <p>${product.descr}</p>
                <p>Price: ${product.price} kr</p>
                <button>Lägg i kundvagn</button>
            </div>
        </c:forEach>

    </c:if>

    <%-- This shows a message if the products list IS empty --%>
    <c:if test="${empty products}">
        <p>No products were found.</p>
    </c:if>

</div>