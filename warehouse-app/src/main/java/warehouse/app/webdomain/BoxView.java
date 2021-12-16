package warehouse.app.webdomain;

import java.util.Set;

import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;

public class BoxView {
  private long id;
  private long storageRoomId;
  private long size;

  public void setId(long id) {
    this.id = id;
  }

  public void setStorageRoomId(long storageRoomId) {
    this.storageRoomId = storageRoomId;
  }

  public void setSize(long roomSize) {
    this.size = roomSize;
  }

  public void setMaterials(Set<Material> materials) {
    this.materials = materials;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  private Set<Material> materials;
  private Set<Category> categories;

  public long getId() {
    return this.id;
  }

  public long getStorageRoomId() {
    return this.storageRoomId;
  }

  public long getSize() {
    return this.size;
  }

  public Set<Material> getMaterials() {
    return this.materials;
  }

  public Set<Category> getCategories() {
    return this.categories;
  }

}
