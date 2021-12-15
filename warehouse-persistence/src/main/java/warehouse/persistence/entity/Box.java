package warehouse.persistence.entity;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
  @LazyCollection(LazyCollectionOption.FALSE)
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private Set<Material> materials;
  @LazyCollection(LazyCollectionOption.FALSE)
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private Set<Category> categories;

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

  public Set<Material> getMaterials() {
    return this.materials;
  }

  public void setMaterials(Set<Material> materials) {
    this.materials = materials;
  }

  public Set<Category> getCategories() {
    return this.categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }
}
