package warehouse.domain;

import java.util.List;

public class Box {
  private Long id;
  private Size size;
  private Customer owner;
  private StorageRoom storageRoom;
  private List<Material> materials;
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
