package models;

import java.sql.Timestamp;

public class Comment {
  private Integer   commentId;
  private Integer   userId;
  private Integer   productId;
  private String    content;
  private Timestamp cDate;
  
  public Integer getProductId() {
    return productId;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getContent() {
    return content;
  }

  public Integer getCommentId() {
    return commentId;
  }

  public Timestamp getCDate() {
    return cDate;
  }
  
   public void setProductId(Integer productId) {
    this.productId = productId;
  }

   public void setUserId(Integer userId) {
    this.userId = userId;
  }

   public void setContent(String content) {
    this.content = content;
  }

   public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

   public void setCDate(Timestamp cDate) {
    this.cDate = cDate;
  }
    
}
