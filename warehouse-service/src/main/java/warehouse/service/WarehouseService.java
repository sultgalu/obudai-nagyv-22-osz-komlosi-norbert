package warehouse.service;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;

public interface WarehouseService {
  void saveData();

  void initializeData();

  boolean authenticate(String username, String password);

  boolean isLoggedIn();

  Customer getLoggedInCustomer();

  void logout();

  Warehouse getWarehouse();

  StorageRoom getStorageRoom(Long storageRoomId);

  void rentStorageRoom(Long storageRoomId);

  void cancelStorageRoomRending(Long storageRoomId);

  void storeBox(Box box, Long storageRoomId);

  void removeBox(Long boxId);
}
