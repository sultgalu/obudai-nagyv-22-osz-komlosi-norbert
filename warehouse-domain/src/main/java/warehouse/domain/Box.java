package warehouse.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Box {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Embedded
  private Size size;
  @ManyToOne
  private Customer owner;
  @ManyToOne
  private StorageRoom storageRoom;
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<Material> materials;
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<Category> categories;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Size getSize() {
    return this.size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public Customer getOwner() {
    return this.owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  public StorageRoom getStorageRoom() {
    return this.storageRoom;
  }

  public void setStorageRoom(StorageRoom storageRoom) {
    this.storageRoom = storageRoom;
  }

  public List<Material> getMaterials() {
    return this.materials;
  }

  public void setMaterials(List<Material> materials) {
    this.materials = materials;
  }

  public List<Category> getCategories() {
    return this.categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
