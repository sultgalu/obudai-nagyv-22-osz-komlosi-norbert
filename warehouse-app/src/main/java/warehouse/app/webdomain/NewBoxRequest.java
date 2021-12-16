package warehouse.app.webdomain;

import java.util.Set;

import warehouse.app.validation.CategoriesConstraint;
import warehouse.app.validation.MaterialsConstraint;
import warehouse.app.validation.SizeConstraint;
import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;

public class NewBoxRequest {
  private long storageRoomId;
  @SizeConstraint
  private String size;
  @MaterialsConstraint
  private Set<Material> materials;
  @CategoriesConstraint
  private Set<Category> categories;

  public long getStorageRoomId() {
    return this.storageRoomId;
  }

  public String getSize() {
    return this.size;
  }

  public Set<Material> getMaterials() {
    return this.materials;
  }

  public void setStorageRoomId(long storageRoomId) {
    this.storageRoomId = storageRoomId;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public void setMaterials(Set<Material> materials) {
    this.materials = materials;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public Set<Category> getCategories() {
    return this.categories;
  }
}
