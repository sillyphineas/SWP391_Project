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
public class Voucher {
    private int VoucherID;
    private String VoucherCode;
    private String VoucherType;
    private String Description;
    private String DiscountAmount;
    private Date StartDate;
    private Date ExpiredDate;
    private int UsageLimit;
    private boolean isDisabled;

    public Voucher() {
    }

    public Voucher(int VoucherID, String VoucherCode, String VoucherType, String Description, String DiscountAmount, Date StartDate, Date ExpiredDate, int UsageLimit, boolean isDisabled) {
        this.VoucherID = VoucherID;
        this.VoucherCode = VoucherCode;
        this.VoucherType = VoucherType;
        this.Description = Description;
        this.DiscountAmount = DiscountAmount;
        this.StartDate = StartDate;
        this.ExpiredDate = ExpiredDate;
        this.UsageLimit = UsageLimit;
        this.isDisabled = isDisabled;
    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int VoucherID) {
        this.VoucherID = VoucherID;
    }

    public String getVoucherCode() {
        return VoucherCode;
    }

    public void setVoucherCode(String VoucherCode) {
        this.VoucherCode = VoucherCode;
    }

    public String getVoucherType() {
        return VoucherType;
    }

    public void setVoucherType(String VoucherType) {
        this.VoucherType = VoucherType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(String DiscountAmount) {
        this.DiscountAmount = DiscountAmount;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getExpiredDate() {
        return ExpiredDate;
    }

    public void setExpiredDate(Date ExpiredDate) {
        this.ExpiredDate = ExpiredDate;
    }

    public int getUsageLimit() {
        return UsageLimit;
    }

    public void setUsageLimit(int UsageLimit) {
        this.UsageLimit = UsageLimit;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Voucher{" + "VoucherID=" + VoucherID + ", VoucherCode=" + VoucherCode + ", VoucherType=" + VoucherType + ", Description=" + Description + ", DiscountAmount=" + DiscountAmount + ", StartDate=" + StartDate + ", ExpiredDate=" + ExpiredDate + ", UsageLimit=" + UsageLimit + ", isDisabled=" + isDisabled + '}';
    }
    
    
}
