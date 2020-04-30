<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<c:url value="/addUser" var="addUrl"/>
<c:url value="/editUser" var="editUrl"/>
<div align="center">
    <p>User page</p>
    <form action="${empty user.password ? addUrl : editUrl}" name="user" method="POST">
        <p><input type="text" name="login" placeholder="Login" value="${user.login}" maxlength="50" required>
        <p><input type="password" name="password" placeholder="Password" value="${user.password}" maxlength="50" required>
        <p><input type="number" name="money" placeholder="Money" value="${user.money}" maxlength="20" required>
        <p><input type="submit" value="Submit"></p>
    </form>
</div>
</body>
</html>
