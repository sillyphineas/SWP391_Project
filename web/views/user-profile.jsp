<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <table>
        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${user.name}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th>Phone</th>
            <td>${user.phoneNumber}</td>
        </tr>
        <tr>
            <th>Gender</th>
            <td>${user.gender ? 'Male' : 'Female'}</td>
        </tr>
        <tr>
            <th>Address</th>
            <td>${user.address}</td>
        </tr>
        <tr>
            <th>Date of Birth</th>
            <td>${user.dateOfBirth}</td>
        </tr>
    </table>
</body>
</html>
