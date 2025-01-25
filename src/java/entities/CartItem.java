/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author HP
 */
public class CartItem {
    	private int CartItemID  ,
	CartID                  ,
	ProductID               ;
	private Double Price    ;
	private int Quantity;
	private double DiscountAmount,
	TotalPrice  ;
        private boolean  isDisabled  ;
    private Product product;

    public CartItem(int CartItemID, int CartID, int ProductID, Double Price, int Quantity, double DiscountAmount, double TotalPrice, boolean isDisabled) {
        this.CartItemID = CartItemID;
        this.CartID = CartID;
        this.ProductID = ProductID;
        this.Price = Price;
        this.Quantity = Quantity;
        this.DiscountAmount = DiscountAmount;
        this.TotalPrice = TotalPrice;
        this.isDisabled = isDisabled;
    }

    public CartItem(int CartItemID, int ProductID, Double Price, int Quantity, double TotalPrice) {
        this.CartItemID = CartItemID;
        this.ProductID = ProductID;
        this.Price = Price;
        this.Quantity = Quantity;
        this.TotalPrice = TotalPrice;
    }

    public CartItem() {
    }
    
    
    public int getCartItemID() {
        return CartItemID;
    }

    public void setCartItemID(int CartItemID) {
        this.CartItemID = CartItemID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double DiscountAmount) {
        this.DiscountAmount = DiscountAmount;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    @Override
    public String toString() {
        return "CartItem{" + "CartItemID=" + CartItemID + ", CartID=" + CartID + ", ProductID=" + ProductID + ", Price=" + Price + ", Quantity=" + Quantity + ", DiscountAmount=" + DiscountAmount + ", TotalPrice=" + TotalPrice + ", isDisabled=" + isDisabled + '}';
    }

    public void setDisabled(boolean aBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
        
        
}
