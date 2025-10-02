<%--
  This line imports the JSTL core library, which gives us access to tags like <c:forEach>.
--%>
<%-- Use the modern Jakarta EE JSTL taglib URI --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%@ page contentType="text-html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Our Products</title>
        <style>
            .product-container { display: flex; flex-wrap: wrap; gap: 20px; }
            .product { border: 1px solid #ccc; padding: 15px; border-radius: 5px; width: 200px; }
            .error-message { color: red; font-weight: bold; border: 1px solid red; padding: 10px; }
        </style>
    </head>
    <body>

        <h1>Our Products</h1>

            <%--
              This <c:choose> block checks for the conditions set by the servlet.
            --%>
        <c:choose>
            <%--
              WHEN condition: Check if the 'errorMessage' attribute is not empty.
              If the servlet's catch block ran, this will be true.
            --%>
            <c:when test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:when>

            <%--
              OTHERWISE condition: If there was no error message, proceed with displaying products.
            --%>
            <c:otherwise>
                <div class="product-container">
                        <%-- A nested check to see if the products list is actually empty or not --%>
                    <c:if test="${not empty products}">
                        <c:forEach items="${products}" var="product">
                            <div class="product">
                                <h3>${product.name}</h3>
                                <p>${product.description}</p>
                                <p>Price: ${product.price} kr</p>
                                <button>Lägg i kundvagn</button>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty products}">
                        <p>No products were found.</p>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>

    </body>
</html>