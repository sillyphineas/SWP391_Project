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
public class Payment {
    private int PaymentID;
    private int OrderID;
    private String PaymentMethod;
    private String PaymentStatus;
    private Date PaymentDate;
    private boolean isDisabled;

    public Payment() {
    }

    public Payment(int PaymentID, int OrderID, String PaymentMethod, String PaymentStatus, Date PaymentDate, boolean isDisabled) {
        this.PaymentID = PaymentID;
        this.OrderID = OrderID;
        this.PaymentMethod = PaymentMethod;
        this.PaymentStatus = PaymentStatus;
        this.PaymentDate = PaymentDate;
        this.isDisabled = isDisabled;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int PaymentID) {
        this.PaymentID = PaymentID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Payment{" + "PaymentID=" + PaymentID + ", OrderID=" + OrderID + ", PaymentMethod=" + PaymentMethod + ", PaymentStatus=" + PaymentStatus + ", PaymentDate=" + PaymentDate + ", isDisabled=" + isDisabled + '}';
    }
    
    
}
