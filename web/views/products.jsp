<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
    <style>
        /* CSS trực tiếp */
        .container {
            display: flex;
        }
        .sidebar {
            width: 25%;
            padding: 20px;
            background-color: #f5f5f5;
            border-right: 1px solid #ddd;
        }
        .product-list {
            width: 75%;
            padding: 20px;
        }
        .product-item {
            border: 1px solid #ddd;
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .product-details {
            flex: 1;
        }
        .product-actions {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .product-actions button {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }
        .product-actions button:hover {
            background-color: #0056b3;
        }
        .pagination {
            margin-top: 20px;
            text-align: center;
        }
        .pagination a {
            margin: 0 5px;
            padding: 5px 10px;
            text-decoration: none;
            border: 1px solid #ddd;
            color: #007bff;
            border-radius: 3px;
        }
        .pagination a.active {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .pagination a:hover {
            background-color: #0056b3;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <form action="/products" method="get">
                <input type="text" name="search" placeholder="Search products..." style="width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 5px;">
                <button type="submit" style="width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">Search</button>
            </form>
            <h3>Latest Products</h3>
            <ul>
                <c:forEach var="latest" items="${latestProducts}">
                    <li>${latest.name} - $${latest.price}</li>
                </c:forEach>
            </ul>
            <h3>Contact</h3>
            <p>Email: nguyenhoainamfpt@gmail.com</p>
            <p>Phone: 0909090909</p>
            <p>Address: Hoa Lac </p>
        </div>
        <!-- Product List -->
        <div class="product-list">
            <h2>Product List</h2>
            <c:forEach var="product" items="${productList}">
                <div class="product-item">
                    <div class="product-details">
                        <h3><a href="ProductDetails?id=${product.id}">${product.name}</a></h3>
                        <p>Price: $${product.price}</p>
                        <p>${product.description}</p>
                    </div>
                    <div class="product-actions">
                        <button onclick="location.href='AddToProduct?id=${product.id}'">Add to Product</button>
                        <button onclick="location.href='Feedback?id=${product.id}'">Feedback</button>
                    </div>
                </div>
            </c:forEach>
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage-1}" class="pre">Previous</a>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage+1}" class="next">Next</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
