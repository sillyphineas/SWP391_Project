/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;


/**
 *
 * @author HP
 */
public class User {
    private int id;                // ID của user
    private String name;           // Tên user
    private String email;          // Email user
    private String passHash;       // Mật khẩu (hash)
    private boolean gender;        // Giới tính (true = Nam, false = Nữ)
    private String phoneNumber;    // Số điện thoại
    private String resetToken;     // Mã reset mật khẩu
    private Date resetTokenExpired;// Ngày hết hạn mã reset
    private String address;        // Địa chỉ
    private Date dateOfBirth;      // Ngày sinh
    private int roleId;            // Vai trò (role ID)
    private boolean isDisabled;    // Trạng thái vô hiệu hóa

    // Constructor không tham số
    public User() {
    }

    // Constructor đầy đủ tham số
    public User(int id, String name, String email, String passHash, boolean gender, String phoneNumber, String resetToken, 
                 Date resetTokenExpired, String address, Date dateOfBirth, int roleId, boolean isDisabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passHash = passHash;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.resetToken = resetToken;
        this.resetTokenExpired = resetTokenExpired;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.roleId = roleId;
        this.isDisabled = isDisabled;
    }

    // Getters và Setters cho từng thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Date getResetTokenExpired() {
        return resetTokenExpired;
    }

    public void setResetTokenExpired(Date resetTokenExpired) {
        this.resetTokenExpired = resetTokenExpired;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    // Override toString() để dễ dàng hiển thị thông tin user
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passHash='" + passHash + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", resetTokenExpired=" + resetTokenExpired +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", roleId=" + roleId +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
