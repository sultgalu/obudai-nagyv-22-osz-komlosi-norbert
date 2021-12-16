package warehouse.service;

import org.springframework.stereotype.Component;

import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Customer;
import warehouse.persistence.entity.StorageRoom;

@Component
public interface WarehouseService {
  void saveData();

  void initializeData();

  boolean authenticate(String username, String password);

  boolean isLoggedIn();

  Customer getLoggedInCustomer();

  void logout();

  public Iterable<Box> getMyBoxes();

  public Iterable<StorageRoom> getStorageRooms();

  public Iterable<StorageRoom> getMyStorageRooms();

  StorageRoom getStorageRoom(Long storageRoomId);

  void rentStorageRoom(Long storageRoomId)
    throws PermissionException, InvalidParameterException, StorageRoomIsNotFreeException;

  void cancelStorageRoomRending(Long storageRoomId) throws PermissionException, InvalidParameterException;

  void storeBox(Box box, Long storageRoomId) throws PermissionException, InvalidParameterException;

  void removeBox(Long boxId) throws PermissionException, InvalidParameterException;
}
