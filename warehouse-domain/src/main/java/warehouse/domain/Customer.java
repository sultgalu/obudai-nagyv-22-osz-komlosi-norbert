package warehouse.domain;

import java.util.List;

public class Customer {
  private Long id;
  private String username;
  private String password;
  private List<StorageRoom> storageRooms;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<StorageRoom> getStorageRooms() {
    return this.storageRooms;
  }

  public void setStorageRooms(List<StorageRoom> storageRooms) {
    this.storageRooms = storageRooms;
  }
}
