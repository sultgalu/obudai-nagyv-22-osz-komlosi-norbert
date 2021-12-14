package warehouse.domain;

import java.util.List;

public class Customer {
  public Long id;
  public String username;
  public String password;
  public List<StorageRoom> storageRooms;
}
