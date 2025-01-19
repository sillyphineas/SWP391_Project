/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Shipping {
    private int ShippingID;
    private int OrderID;
    private int ShipperID;
    private String ShippingStatus;
    private Date EstimatedArrival;     
    private Date ActualArrival;      
    private Date ShippingDate;       

    public Shipping() {
    }

    public Shipping(int ShippingID, int OrderID, int ShipperID, String ShippingStatus, Date EstimatedArrival, Date ActualArrival, Date ShippingDate) {
        this.ShippingID = ShippingID;
        this.OrderID = OrderID;
        this.ShipperID = ShipperID;
        this.ShippingStatus = ShippingStatus;
        this.EstimatedArrival = EstimatedArrival;
        this.ActualArrival = ActualArrival;
        this.ShippingDate = ShippingDate;
    }

    public int getShippingID() {
        return ShippingID;
    }

    public void setShippingID(int ShippingID) {
        this.ShippingID = ShippingID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getShipperID() {
        return ShipperID;
    }

    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    public String getShippingStatus() {
        return ShippingStatus;
    }

    public void setShippingStatus(String ShippingStatus) {
        this.ShippingStatus = ShippingStatus;
    }

    public Date getEstimatedArrival() {
        return EstimatedArrival;
    }

    public void setEstimatedArrival(Date EstimatedArrival) {
        this.EstimatedArrival = EstimatedArrival;
    }

    public Date getActualArrival() {
        return ActualArrival;
    }

    public void setActualArrival(Date ActualArrival) {
        this.ActualArrival = ActualArrival;
    }

    public Date getShippingDate() {
        return ShippingDate;
    }

    public void setShippingDate(Date ShippingDate) {
        this.ShippingDate = ShippingDate;
    }

    @Override
    public String toString() {
        return "Shipping{" + "ShippingID=" + ShippingID + ", OrderID=" + OrderID + ", ShipperID=" + ShipperID + ", ShippingStatus=" + ShippingStatus + ", EstimatedArrival=" + EstimatedArrival + ", ActualArrival=" + ActualArrival + ", ShippingDate=" + ShippingDate + '}';
    }
            
            
}
