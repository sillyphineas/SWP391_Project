/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.List;

/**
 *
 * @author HP
 */
public class Cart {
    	private int CartID    ,
	CustomerID            ;
	private String CartStatus ;
	private double TotalPrice  ;
	private String CreatedAt ,            
	UpdatedAt               ; 

    public Cart(int CartID, int CustomerID, String CartStatus, double TotalPrice, String CreatedAt, String UpdatedAt) {
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        this.CartStatus = CartStatus;
        this.TotalPrice = TotalPrice;
        this.CreatedAt = CreatedAt;
        this.UpdatedAt = UpdatedAt;
    }

    public Cart() {
    }

  

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getCartStatus() {
        return CartStatus;
    }

    public void setCartStatus(String CartStatus) {
        this.CartStatus = CartStatus;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String UpdatedAt) {
        this.UpdatedAt = UpdatedAt;
    }

    @Override
    public String toString() {
        return "Cart{" + "CartID=" + CartID + ", CustomerID=" + CustomerID + ", CartStatus=" + CartStatus + ", TotalPrice=" + TotalPrice + ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + '}';
    }

    public void setCartItems(List<CartItem> cartItemsByCartID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
        
}
