package warehouse.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Customer;
import warehouse.persistence.entity.StorageRoom;
import warehouse.persistence.repository.BoxRepository;
import warehouse.persistence.repository.CustomerRepository;
import warehouse.persistence.repository.StorageRoomRepository;

@ComponentScan(basePackages = { "warehouse.*" })
@Component
public class WarehouseServiceImpl implements WarehouseService {

  // @Autowired
  // private Data data;
  @Autowired
  private CustomerRepository customerRepo;
  @Autowired
  private BoxRepository boxRepo;
  @Autowired
  private StorageRoomRepository storageRepo;

  // private Warehouse warehouse;
  private long loggedInId = 0;

  @Override
  public void saveData() {
    // this.data.save(this.warehouse);
  }

  @Override
  public void initializeData() {
    // this.warehouse = this.data.load();
  }

  @Override
  public boolean authenticate(String username, String password) {
    for (Customer c : this.customerRepo.findAll()) {
      if ((c.getUsername().equals(username)) && (c.getPassword().equals(password))) {
        this.loggedInId = c.getId();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLoggedIn() {
    return this.loggedInId != 0;
  }

  @Override
  public Customer getLoggedInCustomer() {
    return this.customerRepo.findById(this.loggedInId).orElse(null);
  }

  @Override
  public void logout() {
    this.loggedInId = 0;
  }

  @Override
  public Iterable<StorageRoom> getStorageRooms() {
    return (this.storageRepo.findAll());
  }

  @Override
  public StorageRoom getStorageRoom(Long storageRoomId) {
    return this.storageRepo.findById(storageRoomId).orElse(null);
  }

  @Override
  @Transactional
  public Iterable<StorageRoom> getMyStorageRooms() {
    return new ArrayList<>(getLoggedInCustomer().getStorageRooms());
  }

  @Transactional
  @Override
  public void rentStorageRoom(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if ((sr.getOwner() == null) || (sr.getOwner().getId() != this.loggedInId)) {
      // sr.setOwner(this.customerRepo.findById(this.loggedInId).orElse(null));
      sr.setFree(false);
      sr.setOwner(getLoggedInCustomer());
      // getLoggedInCustomer().getStorageRooms().add(sr);
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  @Transactional
  public void cancelStorageRoomRending(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if ((sr.getOwner() != null) && (sr.getOwner().getId() == this.loggedInId)) {
      sr.setOwner(null);
      sr.setFree(true);
      // this.customerRepo.findById(sr.getOwner().getId()).get().getStorageRooms().removeIf(null)
      // sr.getOwner().getStorageRooms().removeIf(x -> x.getId() == sr.getId());
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  @Transactional
  public void storeBox(Box box, Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if ((sr.getOwner() != null) && (sr.getOwner().getId() == this.loggedInId)) {
      box.setStorageRoom(sr);
      box.setOwner(sr.getOwner());
      sr.getBoxes().add(box);
      this.boxRepo.save(box);
      // this.warehouse.getBoxes().add(box);
    } else {
      throw new PermissionException("You are not the owner of this storage room.");
    }
  }

  @Override
  @Transactional
  public void removeBox(Long boxId) throws PermissionException, InvalidParameterException {
    Box box = this.boxRepo.findById(boxId).orElse(null);
    if (box == null) {
      throw new InvalidParameterException("Box with the given id does not exist");
    }
    if ((box.getOwner() != null) && (box.getOwner().getId() == this.loggedInId)) {
      this.boxRepo.delete(box);
    } else {
      throw new PermissionException("You are not the owner of this box. You cannot remove it.");
    }
  }

}
