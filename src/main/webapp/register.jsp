<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrera dig</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Registrera dig</h2>

<form action="register" method="post">
    <label for="username">Användarnamn:</label>
    <input type="text" id="username" name="username" required><br/><br/>

    <label for="password">Lösenord:</label>
    <input type="password" id="password" name="password" required><br/><br/>

    <label for="firstname">Förnamn:</label>
    <input type="text" id="firstname" name="firstname" required><br/><br/>

    <label for="lastname">Efternamn:</label>
    <input type="text" id="lastname" name="lastname" required><br/><br/>

    <label for="email">E-post:</label>
    <input type="email" id="email" name="email" required><br/><br/>





    <button type="submit">Registrera</button>
</form>

<p>Har du redan ett konto? <a href="login.jsp">Logga in här</a></p>
<%@ include file="footer.jsp" %>
</body>
</html>
