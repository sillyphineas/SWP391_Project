<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Detail</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="blog-detail">
        <!-- Kiểm tra xem có thông báo lỗi hay không -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                <p>${errorMessage}</p>
            </div>
        </c:if>

        <!-- Kiểm tra xem có blog hay không -->
        <c:if test="${not empty blog}">
            <h1>${blog.title}</h1>
            <p><strong>Author:</strong> ${blog.authorID}</p>
            <p><strong>Posted on:</strong> ${blog.postTime}</p>
            <div>
                <img src="${blog.imageURL}" alt="${blog.title}" style="max-width: 100%;">
            </div>
            <div>
<!--                <p><strong>Status:</strong> ${blog.status}</p>-->
                <p><strong>Content:</strong> ${blog.content}</p>
            </div>
            <c:if test="${blog.isDisabled}">
                <p>This blog is disabled.</p>
            </c:if>
        </c:if>
    </div>
</body>
</html>
