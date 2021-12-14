package warehouse.service;

import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;

@Component
public class WarehouseServiceImpl implements WarehouseService {

  @Override
  public void saveData() {
    // TODO Auto-generated method stub

  }

  @Override
  public void initializeData() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean authenticate(String username, String password) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isLoggedIn() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Customer getLoggedInCustomer() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void logout() {
    // TODO Auto-generated method stub

  }

  @Override
  public Warehouse getWarehouse() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public StorageRoom getStorageRoom(Long storageRoomId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void rentStorageRoom(Long storageRoomId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void cancelStorageRoomRending(Long storageRoomId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void storeBox(Box box, Long storageRoomId) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeBox(Long boxId) {
    // TODO Auto-generated method stub

  }

}
