<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <title>Kassa</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        main {
            flex-grow: 1;
        }
        .checkout-container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        h1, h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .summary-table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        .summary-table th, .summary-table td {
            padding: 12px;
            border-bottom: 1px solid #eee;
            text-align: left;
        }
        .summary-table .total-row td {
            font-weight: bold;
            font-size: 1.2em;
            border-top: 2px solid #333;
        }

        .payment-options fieldset {
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 10px;
        }
        .payment-options legend {
            font-weight: bold;
        }

        .submit-btn {
            display: block;
            width: 100%;
            padding: 15px;
            margin-top: 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .submit-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<%@ include file="header.jsp" %>

<main>
    <div class="checkout-container">
        <h1>Kassa</h1>
        <c:if test="${not empty success}">
            <p style="color: green; text-align: center; font-weight: bold;">
                    ${success}
            </p>
        </c:if>

        <c:if test="${not empty error}">
            <p style="color: red; text-align: center; font-weight: bold;">
                    ${error}
            </p>
        </c:if>

        <form action="checkout" method="POST" >

            <h2>Din beställning</h2>
            <table class="summary-table">
                <c:set var="totalSum" value="0" />
                <c:forEach items="${sessionScope.shoppingcart.products}" var="item">
                    <tr>
                        <td>${item.name}</td>
                        <td style="text-align:right;">${item.price} kr</td>
                    </tr>
                    <c:set var="totalSum" value="${totalSum + item.price}" />
                </c:forEach>
                <tr class="total-row">
                    <td>Totalt att betala</td>
                    <td style="text-align:right;">${totalSum} kr</td>
                </tr>
            </table>

            <hr style="margin: 30px 0;">


            <div class="order-button-container">
                <button type="submit" class="submit-btn">Bekräfta och slutför köp</button>
            </div>


        </form>
    </div>
</main>

<%@ include file="footer.jsp" %>

</body>
</html>