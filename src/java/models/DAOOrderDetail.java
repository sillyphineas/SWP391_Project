/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import entities.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class DAOOrderDetail extends DBConnection{
    // Add a new order detail
    public int addOrderDetail(OrderDetail orderDetail) {
        int result = 0;
        String sql = "INSERT INTO OrderDetails (orderID, productID, quantity, productPrice) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderDetail.getOrderID());
            preparedStatement.setInt(2, orderDetail.getProductID());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setDouble(4, orderDetail.getProductPrice());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get all order details for a given order
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails WHERE orderID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                double productPrice = rs.getDouble("productPrice");

                orderDetails.add(new OrderDetail(orderID, productID, quantity, productPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    // Get order detail by orderID and productID
    public OrderDetail getOrderDetail(int orderID, int productID) {
        OrderDetail orderDetail = null;
        String sql = "SELECT * FROM OrderDetails WHERE orderID = ? AND productID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                double productPrice = rs.getDouble("productPrice");

                orderDetail = new OrderDetail(orderID, productID, quantity, productPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }

    // Update an order detail
    public int updateOrderDetail(OrderDetail orderDetail) {
        int result = 0;
        String sql = "UPDATE OrderDetails SET quantity = ?, productPrice = ? WHERE orderID = ? AND productID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderDetail.getQuantity());
            preparedStatement.setDouble(2, orderDetail.getProductPrice());
            preparedStatement.setInt(3, orderDetail.getOrderID());
            preparedStatement.setInt(4, orderDetail.getProductID());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete an order detail by orderID and productID
    public int deleteOrderDetail(int orderID, int productID) {
        int result = 0;
        String sql = "DELETE FROM OrderDetails WHERE orderID = ? AND productID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Test the DAOOrderDetail
    public static void main(String[] args) {
//        DAOOrderDetail daoOrderDetail = new DAOOrderDetail();
//
//        // Add a new order detail
//        OrderDetail orderDetail = new OrderDetail(1, 101, 2, 50.00);
//        daoOrderDetail.addOrderDetail(orderDetail);
//
//        // Get all order details for a given order
//        List<OrderDetail> orderDetails = daoOrderDetail.getOrderDetailsByOrderID(1);
//        for (OrderDetail od : orderDetails) {
//            System.out.println(od);
//        }
//
//        // Update an order detail
//        orderDetail.setQuantity(3);
//        daoOrderDetail.updateOrderDetail(orderDetail);
//
//        // Get order detail by orderID and productID
//        OrderDetail retrievedOrderDetail = daoOrderDetail.getOrderDetail(1, 101);
//        System.out.println("Retrieved OrderDetail: " + retrievedOrderDetail);
//
//        // Delete an order detail
//        daoOrderDetail.deleteOrderDetail(1, 101);
    }
}
