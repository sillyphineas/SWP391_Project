/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import entities.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class DAOOrder extends DBConnection {

    // Add a new order
    public int addOrder(Order order) {
        int result = 0;
        String sql = "INSERT INTO Orders (buyerID, status, orderTime, orderStatus, shippingDate, shippingAddress, " +
                     "totalPrice, discountedPrice, paymentMethod, isDisabled, voucherID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, order.getBuyerID());
            preparedStatement.setByte(2, order.getStatus());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderTime().getTime()));
            preparedStatement.setString(4, order.getOrderStatus());
            preparedStatement.setDate(5, order.getShippingDate() != null ? new java.sql.Date(order.getShippingDate().getTime()) : null);
            preparedStatement.setString(6, order.getShippingAddress());
            preparedStatement.setDouble(7, order.getTotalPrice());
            preparedStatement.setDouble(8, order.getDiscountedPrice());
            preparedStatement.setByte(9, order.getPaymentMethod());
            preparedStatement.setBoolean(10, order.isDisabled());
            preparedStatement.setInt(11, order.getVoucherID() != null ? order.getVoucherID() : null);

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int buyerID = rs.getInt("buyerID");
                byte status = rs.getByte("status");
                Timestamp orderTime = rs.getTimestamp("orderTime");
                String orderStatus = rs.getString("orderStatus");
                Date shippingDate = rs.getDate("shippingDate");
                String shippingAddress = rs.getString("shippingAddress");
                double totalPrice = rs.getDouble("totalPrice");
                double discountedPrice = rs.getDouble("discountedPrice");
                byte paymentMethod = rs.getByte("paymentMethod");
                boolean isDisabled = rs.getBoolean("isDisabled");
                Integer voucherID = rs.getInt("voucherID");

                orders.add(new Order(id, buyerID, status, new Date(orderTime.getTime()), orderStatus, shippingDate,
                        shippingAddress, totalPrice, discountedPrice, paymentMethod, isDisabled, voucherID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Get order by ID
    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM Orders WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int buyerID = rs.getInt("buyerID");
                byte status = rs.getByte("status");
                Timestamp orderTime = rs.getTimestamp("orderTime");
                String orderStatus = rs.getString("orderStatus");
                Date shippingDate = rs.getDate("shippingDate");
                String shippingAddress = rs.getString("shippingAddress");
                double totalPrice = rs.getDouble("totalPrice");
                double discountedPrice = rs.getDouble("discountedPrice");
                byte paymentMethod = rs.getByte("paymentMethod");
                boolean isDisabled = rs.getBoolean("isDisabled");
                Integer voucherID = rs.getInt("voucherID");

                order = new Order(id, buyerID, status, new Date(orderTime.getTime()), orderStatus, shippingDate,
                        shippingAddress, totalPrice, discountedPrice, paymentMethod, isDisabled, voucherID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    // Update an order
    public int updateOrder(Order order) {
        int result = 0;
        String sql = "UPDATE Orders SET buyerID = ?, status = ?, orderTime = ?, orderStatus = ?, shippingDate = ?, " +
                     "shippingAddress = ?, totalPrice = ?, discountedPrice = ?, paymentMethod = ?, isDisabled = ?, voucherID = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, order.getBuyerID());
            preparedStatement.setByte(2, order.getStatus());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderTime().getTime()));
            preparedStatement.setString(4, order.getOrderStatus());
            preparedStatement.setDate(5, order.getShippingDate() != null ? new java.sql.Date(order.getShippingDate().getTime()) : null);
            preparedStatement.setString(6, order.getShippingAddress());
            preparedStatement.setDouble(7, order.getTotalPrice());
            preparedStatement.setDouble(8, order.getDiscountedPrice());
            preparedStatement.setByte(9, order.getPaymentMethod());
            preparedStatement.setBoolean(10, order.isDisabled());
            preparedStatement.setInt(11, order.getVoucherID() != null ? order.getVoucherID() : null);
            preparedStatement.setInt(12, order.getId());

            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Delete an order
    public int deleteOrder(int orderId) {
        int result = 0;
        String sql = "DELETE FROM Orders WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Test the DAOOrder
    public static void main(String[] args) {
//        DAOOrder daoOrder = new DAOOrder();
//
//        // Add a new order
//        Order order = new Order(0, 1, (byte) 1, new Date(), "Processing", null, "123 Street, City", 500.00, 450.00, (byte) 1, false, null);
//        daoOrder.addOrder(order);
//
//        // Get all orders
//        List<Order> orders = daoOrder.getAllOrders();
//        for (Order o : orders) {
//            System.out.println(o);
//        }
//
//        // Update an order
//        order.setStatus((byte) 2);
//        daoOrder.updateOrder(order);
//
//        // Get order by ID
//        Order retrievedOrder = daoOrder.getOrderById(1);
//        System.out.println("Retrieved Order: " + retrievedOrder);
//
//        // Delete an order
//        daoOrder.deleteOrder(1);
    }
}
