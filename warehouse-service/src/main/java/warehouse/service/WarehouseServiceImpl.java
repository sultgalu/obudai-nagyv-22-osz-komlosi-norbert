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
    return this.warehouse.storageRooms.stream().filter(sr -> sr.id == storageRoomId).findFirst().orElse(null);
  }

  @Override
  public void rentStorageRoom(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if (sr.owner != this.loggedIn) {
      sr.owner = this.loggedIn;
      sr.isFree = false;
      this.loggedIn.storageRooms.add(getStorageRoom(storageRoomId));
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  public void cancelStorageRoomRending(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if (sr.owner == this.loggedIn) {
      sr.owner = null;
      sr.isFree = true;
      this.loggedIn.storageRooms.removeIf(x -> x.id == storageRoomId);
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  public void storeBox(Box box, Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if (sr.owner == this.loggedIn) {
      box.storageRoom = sr;
      box.owner = this.loggedIn;
      sr.boxes.add(box);
      this.warehouse.boxes.add(box);
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  public void removeBox(Long boxId) throws PermissionException, InvalidParameterException {
    Box box = this.warehouse.boxes.stream().filter(sr -> sr.id == boxId).findFirst().orElse(null);
    if (box == null) {
      throw new InvalidParameterException("Box with the given id does not exist");
    }
    if (box.owner == this.loggedIn) {
      this.warehouse.boxes.removeIf(b -> b.id == boxId);
      this.warehouse.storageRooms.forEach(sr -> {
        sr.boxes.removeIf(b -> b.id == boxId);
      });
    } else {
      throw new PermissionException("You are not the owner of this box. You cannot remove it.");
    }
  }

}
