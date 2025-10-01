<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kundvagn</title>
    <style>
        body { font-family: Arial, sans-serif; }
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .back-button {
            display: block;
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<h2 style="text-align:center;">Din kundvagn</h2>

<table>
    <tr>
        <th>Produkt</th>
        <th>Antal</th>
        <th>Pris</th>
        <th>Totalt</th>
    </tr>
    <!-- Just nu dummy-data -->
    <tr>
        <td>Produkt 1</td>
        <td>2</td>
        <td>100 kr</td>
        <td>200 kr</td>
    </tr>
    <tr>
        <td>Produkt 2</td>
        <td>1</td>
        <td>150 kr</td>
        <td>150 kr</td>
    </tr>
    <tr>
        <td colspan="3"><b>Summa</b></td>
        <td><b>350 kr</b></td>
    </tr>
</table>

<div class="back-button">
    <form action="index.jsp" method="get">
        <button type="submit">Tillbaka till startsidan</button>
    </form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
