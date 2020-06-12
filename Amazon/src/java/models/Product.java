package models;


public class  Product {
  private Integer productId;
  private Integer userId;
  private String  name;
  private String  description;
  private Integer stock;
  private Double  price;

  public Integer getProductId() {
    return productId;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Integer getStock() {
    return stock;
  }

  public Double getPrice() {
    return price;
  }
  
   public void setProductId(Integer productId) {
    this.productId = productId;
  }

   public void setUserId(Integer userId) {
    this.userId = userId;
  }

   public void setName(String name) {
    this.name = name;
  }

   public void setDescription(String description) {
    this.description = description;
  }

   public void setStock(Integer stock) {
    this.stock = stock;
  }

   public void setPrice(Double price) {
    this.price = price;
  }
    
}
