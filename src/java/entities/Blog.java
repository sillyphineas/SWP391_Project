/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author HP
 */
public class Blog {
    private int id           ;
    private int authorID   ;
 
    private String postTime  ,
    title                   ,
    content                 ,
    imageURL                ,
    status                  ;
    private boolean isDisabled  ;   

    public Blog(int id, int authorID, String postTime, String title, String content, String imageURL, String status, boolean isDisabled) {
        this.id = id;
        this.authorID = authorID;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.imageURL = imageURL;
        this.status = status;
        this.isDisabled = isDisabled;
    }

    public Blog() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", authorID=" + authorID + ", postTime=" + postTime + ", title=" + title + ", content=" + content + ", imageURL=" + imageURL + ", status=" + status + ", isDisabled=" + isDisabled + '}';
    }
    
    
}
