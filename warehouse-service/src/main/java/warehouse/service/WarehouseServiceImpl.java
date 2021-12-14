package warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;
import warehouse.persistence.Data;

@Component
@ComponentScan(basePackages = { "warehouse.persistence" })
public class WarehouseServiceImpl implements WarehouseService {

  @Autowired
  private Data data;
  private Warehouse warehouse;
  private Customer loggedIn;

  @Override
  public void saveData() {
    this.data.save(this.warehouse);
  }

  @Override
  public void initializeData() {
    this.warehouse = this.data.load();
  }

  @Override
  public boolean authenticate(String username, String password) {
    for (Customer c : this.warehouse.customers) {
      if ((c.username.equals(username)) && (c.password.equals(password))) {
        this.loggedIn = c;
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLoggedIn() {
    return this.loggedIn != null;
  }

  @Override
  public Customer getLoggedInCustomer() {
    return this.loggedIn;
  }

  @Override
  public void logout() {
    this.loggedIn = null;
  }

  @Override
  public Warehouse getWarehouse() {
    return this.warehouse;
  }

  @Override
  public StorageRoom getStorageRoom(Long storageRoomId) {
    return this.warehouse.storageRooms.stream().filter(sr -> sr.id == storageRoomId).findFirst().get();
  }

  @Override
  public void rentStorageRoom(Long storageRoomId) {
    getStorageRoom(storageRoomId).owner = this.loggedIn;
    getStorageRoom(storageRoomId).isFree = false;
  }

  @Override
  public void cancelStorageRoomRending(Long storageRoomId) {
    getStorageRoom(storageRoomId).owner = null;
    getStorageRoom(storageRoomId).isFree = true;
  }

  @Override
  public void storeBox(Box box, Long storageRoomId) {
    getStorageRoom(storageRoomId).boxes.add(box);
  }

  @Override
  public void removeBox(Long boxId) {
    this.warehouse.boxes.removeIf(b -> b.id == boxId);
    this.warehouse.storageRooms.forEach(sr -> {
      sr.boxes.removeIf(b -> b.id == boxId);
    });
  }

}
