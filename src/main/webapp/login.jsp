<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logga in</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Logga in</h2>

<form action="login" method="post">
    <label for="username">Användarnamn:</label>
    <input type="text" id="username" name="username" required><br/><br/>

    <label for="password">Lösenord:</label>
    <input type="password" id="password" name="password" required><br/><br/>

    <button type="submit">Logga in</button>
</form>

<p>Har du inget konto? <a href="register.jsp">Registrera dig här</a></p>
<%@ include file="footer.jsp" %>
</body>
</html>
