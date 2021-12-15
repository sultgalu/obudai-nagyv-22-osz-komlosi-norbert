package warehouse.webdomain;

import java.util.Set;

import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;

public class NewBoxRequest {
  private final long storageRoomId;

  public long getStorageRoomId() {
    return this.storageRoomId;
  }

  public String getSize() {
    return this.size;
  }

  public Set<Material> getMaterials() {
    return this.materials;
  }

  public NewBoxRequest(long storageRoomId, String size, Set<Material> materials, Set<Category> categories) {
    super();
    this.storageRoomId = storageRoomId;
    this.size = size;
    this.materials = materials;
    this.categories = categories;
  }

  public Set<Category> getCategories() {
    return this.categories;
  }

  private final String size;
  private final Set<Material> materials;
  private final Set<Category> categories;
}
