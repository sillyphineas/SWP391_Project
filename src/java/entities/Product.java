/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author HP
 */
public class Product {
    private int id;
    private int brandID;
    private String name;
    private double price;
    private int stock;
    private String description;
    private boolean isDisabled;
    private int feedbackCount;
    private String status;
    private String imageURL;
    private String chipset;
    private int ram;
    private int storage;
    private double screenSize;
    private String screenType;
    private String resolution;
    private int batteryCapacity;
    private String cameraSpecs;
    private String os;
    private String simType;
    private String connectivity;

    public Product() {
    }

    public Product(int id, int brandID, String name, double price, int stock, String description, boolean isDisabled, int feedbackCount, String status, String imageURL, String chipset, int ram, int storage, double screenSize, String screenType, String resolution, int batteryCapacity, String cameraSpecs, String os, String simType, String connectivity) {
        this.id = id;
        this.brandID = brandID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.isDisabled = isDisabled;
        this.feedbackCount = feedbackCount;
        this.status = status;
        this.imageURL = imageURL;
        this.chipset = chipset;
        this.ram = ram;
        this.storage = storage;
        this.screenSize = screenSize;
        this.screenType = screenType;
        this.resolution = resolution;
        this.batteryCapacity = batteryCapacity;
        this.cameraSpecs = cameraSpecs;
        this.os = os;
        this.simType = simType;
        this.connectivity = connectivity;
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(int feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getCameraSpecs() {
        return cameraSpecs;
    }

    public void setCameraSpecs(String cameraSpecs) {
        this.cameraSpecs = cameraSpecs;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", brandID=" + brandID + ", name=" + name + ", price=" + price + ", stock=" + stock + ", description=" + description + ", isDisabled=" + isDisabled + ", feedbackCount=" + feedbackCount + ", status=" + status + ", imageURL=" + imageURL + ", chipset=" + chipset + ", ram=" + ram + ", storage=" + storage + ", screenSize=" + screenSize + ", screenType=" + screenType + ", resolution=" + resolution + ", batteryCapacity=" + batteryCapacity + ", cameraSpecs=" + cameraSpecs + ", os=" + os + ", simType=" + simType + ", connectivity=" + connectivity + '}';
    }
    
    
}
