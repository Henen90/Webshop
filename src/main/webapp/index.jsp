<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webbshop - Startsida</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        main {
            padding: 20px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin: 10px;
            display: inline-block;
            width: 200px;
            text-align: center;
        }
        .product button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .product button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <h2>Produkter</h2>

    <jsp:include page="products.jsp"/>



</main>
<%@ include file="footer.jsp" %>
</body>

</html>
