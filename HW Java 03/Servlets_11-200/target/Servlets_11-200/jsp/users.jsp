<%@ page import="itis.models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Users</title>
</head>
<body>
<h1>From JSP Users</h1>
<div>
  <table>
    <tr>
      <th>ID</th>
      <th>NAME</th>
      <th>SURNAME</th>
      <th>Username</th>
    </tr>
    <c:forEach items="${usersForJsp}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.nameOfUser}</td>
        <td>${user.surnameOfUser}</td>
        <td>${user.username}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>