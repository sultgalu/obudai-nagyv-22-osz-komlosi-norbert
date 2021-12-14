package warehouse.service;

import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;

@Component
public interface WarehouseService {
  void saveData();

  void initializeData();

  boolean authenticate(String username, String password);

  boolean isLoggedIn();

  Customer getLoggedInCustomer();

  void logout();

  Warehouse getWarehouse();

  StorageRoom getStorageRoom(Long storageRoomId);

  void rentStorageRoom(Long storageRoomId) throws PermissionException, InvalidParameterException;

  void cancelStorageRoomRending(Long storageRoomId) throws PermissionException, InvalidParameterException;

  void storeBox(Box box, Long storageRoomId) throws PermissionException, InvalidParameterException;

  void removeBox(Long boxId) throws PermissionException, InvalidParameterException;
}
