package warehouse.domain;

import java.util.List;

public class Box {
  public Long id;
  public Size size;
  public Customer owner;
  public StorageRoom storageRoom;
  public List<Material> materials;
  public List<Category> categories;
}
