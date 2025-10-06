<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="bo.UserDTO" %>

<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null || (!"ADMIN".equals(user.getRole()) && !"WAREHOUSE_STAFF".equals(user.getRole()))) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>
<html>
<head>
    <title>Administration: Beställningar</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp" %>
<main class="admin-container">
    <h2>Beställningsadministration</h2>

    <c:if test="${not empty sessionScope.message}"><p class="message success">${sessionScope.message}<c:remove var="message" scope="session"/></p></c:if>
    <c:if test="${not empty sessionScope.error}"><p class="message error">${sessionScope.error}<c:remove var="error" scope="session"/></p></c:if>

    <div class="admin-table-wrapper">
        <table>
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Kund</th>
                <th>Status</th>
                <th>Uppdatera Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allOrders}" var="o">
                <tr>
                    <td>${o.orderId}</td>
                    <td>${o.user.username}</td>
                    <td>${o.status}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/admin/updateOrderStatus" method="post" class="inline-form">
                            <input type="hidden" name="orderId" value="${o.orderId}">
                            <select name="newStatus">
                                <option value="PACKING" <c:if test="${o.status eq 'PACKING'}">selected</c:if>>PACKING</option>
                                <option value="PACKED" <c:if test="${o.status eq 'PACKED'}">selected</c:if>>PACKED</option>
                                <option value="SENT" <c:if test="${o.status eq 'SENT'}">selected</c:if>>SENT</option>
                                <option value="DELIVERED" <c:if test="${o.status eq 'DELIVERED'}">selected</c:if>>DELIVERED</option>
                            </select>
                            <button type="submit">Uppdatera</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>