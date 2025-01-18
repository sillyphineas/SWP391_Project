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
public class Feedback {
    private int prorductID;
    private int reviewerID;
    private Date reviewTime;
    private int rating;
    private String content;
    private boolean isDisabled;

    public Feedback() {
    }

    public Feedback(int prorductID, int reviewerID, Date reviewTime, int rating, String content, boolean isDisabled) {
        this.prorductID = prorductID;
        this.reviewerID = reviewerID;
        this.reviewTime = reviewTime;
        this.rating = rating;
        this.content = content;
        this.isDisabled = isDisabled;
    }

    public int getProrductID() {
        return prorductID;
    }

    public void setProrductID(int prorductID) {
        this.prorductID = prorductID;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Feedback{" + "prorductID=" + prorductID + ", reviewerID=" + reviewerID + ", reviewTime=" + reviewTime + ", rating=" + rating + ", content=" + content + ", isDisabled=" + isDisabled + '}';
    }
    
   
}
