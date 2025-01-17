/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Payment;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author HP
 */
public class DAOPayment extends DBConnection {

    public Vector<Payment> getPayments(String sql) {
        Vector<Payment> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("PaymentID"),
                        rs.getInt("OrderID"),
                        rs.getString("PaymentMethod"),
                        rs.getString("PaymentStatus"),
                        rs.getDate("PaymentDate"),
                        rs.getBoolean("isDisabled")
                );
                vector.add(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM Payment WHERE PaymentID = ?";
        Payment payment = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, paymentId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                payment = new Payment(
                        rs.getInt("PaymentID"),
                        rs.getInt("OrderID"),
                        rs.getString("PaymentMethod"),
                        rs.getString("PaymentStatus"),
                        rs.getDate("PaymentDate"),
                        rs.getBoolean("isDisabled")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payment;
    }

}
