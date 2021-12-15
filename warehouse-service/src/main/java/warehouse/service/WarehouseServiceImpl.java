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
    for (Customer c : this.warehouse.getCustomers()) {
      if ((c.getUsername().equals(username)) && (c.getPassword().equals(password))) {
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
    return this.warehouse.getStorageRooms().stream().filter(sr -> sr.getId() == storageRoomId).findFirst().orElse(null);
  }

  @Override
  public void rentStorageRoom(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if (sr.getOwner() != this.loggedIn) {
      sr.setOwner(this.loggedIn);
      sr.setFree(false);
      this.loggedIn.getStorageRooms().add(getStorageRoom(storageRoomId));
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
    if (sr.getOwner() == this.loggedIn) {
      sr.setOwner(null);
      sr.setFree(true);
      this.loggedIn.getStorageRooms().removeIf(x -> x.getId() == storageRoomId);
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
    if (sr.getOwner() == this.loggedIn) {
      box.setStorageRoom(sr);
      box.setOwner(this.loggedIn);
      sr.getBoxes().add(box);
      this.warehouse.getBoxes().add(box);
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  public void removeBox(Long boxId) throws PermissionException, InvalidParameterException {
    Box box = this.warehouse.getBoxes().stream().filter(sr -> sr.getId() == boxId).findFirst().orElse(null);
    if (box == null) {
      throw new InvalidParameterException("Box with the given id does not exist");
    }
    if (box.getOwner() == this.loggedIn) {
      this.warehouse.getBoxes().removeIf(b -> b.getId() == boxId);
      this.warehouse.getStorageRooms().forEach(sr -> {
        sr.getBoxes().removeIf(b -> b.getId() == boxId);
      });
    } else {
      throw new PermissionException("You are not the owner of this box. You cannot remove it.");
    }
  }

}
