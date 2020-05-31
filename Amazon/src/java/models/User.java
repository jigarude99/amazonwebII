package models;

import java.sql.Date;
import java.sql.Timestamp;

public class  User {
  private Integer id;
  private String username;
  private String password;
  private String name;
  private String cedula;
  private String phone;

  public Integer getId() { return id; }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getCedula() {
    return cedula;
  }

  public void setPassword(String password) { this.password = password; }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) { this.id = id; }
}

