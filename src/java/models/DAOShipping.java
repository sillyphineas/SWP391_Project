/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import entities.Shipping;
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
public class DAOShipping extends DBConnection {

    public Vector<Shipping> getShippings(String sql) {
        Vector<Shipping> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Shipping shipping = new Shipping(
                        rs.getInt("ShippingID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ShipperID"),
                        rs.getString("ShippingStatus"),
                        rs.getDate("EstimatedArrival"),
                        rs.getDate("ActualArrival"),
                        rs.getDate("ShippingDate")
                );
                vector.add(shipping);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Shipping getShippingById(int shippingId) {
        String sql = "SELECT * FROM Shipping WHERE ShippingID = ?";
        Shipping shipping = null;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, shippingId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                shipping = new Shipping(
                        rs.getInt("ShippingID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ShipperID"),
                        rs.getString("ShippingStatus"),
                        rs.getDate("EstimatedArrival"),
                        rs.getDate("ActualArrival"),
                        rs.getDate("ShippingDate")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipping.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shipping;
    }

}
