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

    <!-- Just nu "dummy"-produkter -->
    <div class="product">
        <h3>Produkt 1</h3>
        <p>En kort beskrivning av produkt 1</p>
        <button>Lägg i kundvagn</button>
    </div>

    <div class="product">
        <h3>Produkt 2</h3>
        <p>En kort beskrivning av produkt 2</p>
        <button>Lägg i kundvagn</button>
    </div>

    <div class="product">
        <h3>Produkt 3</h3>
        <p>En kort beskrivning av produkt 3</p>
        <button>Lägg i kundvagn</button>
    </div>
</main>

<%@ include file="footer.jsp" %>

</body>
</html>
