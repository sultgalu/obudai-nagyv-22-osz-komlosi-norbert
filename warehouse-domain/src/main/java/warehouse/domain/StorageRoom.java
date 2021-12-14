package warehouse.domain;

import java.util.List;

public class StorageRoom {
  public Long id;
  public boolean isFree;
  public Size size;
  public Customer owner;
  public List<Box> boxes;
}
