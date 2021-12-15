package warehouse.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
  private Set<StorageRoom> storageRooms;

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

  public Set<StorageRoom> getStorageRooms() {
    return this.storageRooms;
  }

  public void setStorageRooms(Set<StorageRoom> storageRooms) {
    this.storageRooms = storageRooms;
  }
}
