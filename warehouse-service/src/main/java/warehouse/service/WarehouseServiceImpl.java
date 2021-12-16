package warehouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  @Autowired
  private CustomerRepository customerRepo;
  @Autowired
  private BoxRepository boxRepo;
  @Autowired
  private StorageRoomRepository storageRepo;
  @Autowired
  private PasswordEncoder encoder;

  @Override
  public void saveData() {
    // this.data.save(this.warehouse);
  }

  @Override
  @Transactional
  public void initializeData() {
    this.customerRepo.findAll().forEach(u -> {
      u.setPassword(this.encoder.encode(u.getPassword()));
    });
    this.customerRepo.findAll().forEach(u -> {
      System.out.println("Pswd: " + u.getPassword());
    });
    // this.warehouse = this.data.load();
  }

  @Override
  public boolean authenticate(String username, String password) {
    for (Customer c : this.customerRepo.findAll()) {
      if ((c.getUsername().equals(username)) && (c.getPassword().equals(password))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLoggedIn() {
    return getLoggedInCustomer() != null;
  }

  @Override
  public Customer getLoggedInCustomer() {
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.customerRepo.findByUsername(principal.getUsername());
  }

  @Override
  public void logout() {

  }

  @Override
  public Iterable<Box> getMyBoxes() {
    List<Box> result = new ArrayList<>();
    this.boxRepo.findAll().forEach(b -> {
      if ((b.getOwner() != null) && (b.getOwner().getId() == getLoggedInCustomer().getId())) {
        result.add(b);
      }
    });
    return result;
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
  public void rentStorageRoom(Long storageRoomId)
    throws PermissionException, InvalidParameterException, StorageRoomIsNotFreeException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if (sr.getOwner() != null) {
      throw new StorageRoomIsNotFreeException();
    }
    sr.setFree(false);
    sr.setOwner(getLoggedInCustomer());
    sr.getBoxes().forEach(b -> b.setOwner(getLoggedInCustomer()));
  }

  @Override
  @Transactional
  public void cancelStorageRoomRending(Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if ((sr.getOwner() == null) || (sr.getOwner().getId() != getLoggedInCustomer().getId())) {
      throw new PermissionException("You are not the owner of this storage room.");
    }
    sr.setOwner(null);
    sr.setFree(true);
    sr.getBoxes().forEach(b -> b.setOwner(null));
  }

  @Override
  @Transactional
  public void storeBox(Box box, Long storageRoomId) throws PermissionException, InvalidParameterException {
    StorageRoom sr = getStorageRoom(storageRoomId);
    if (sr == null) {
      throw new InvalidParameterException("Storage room with the given id does not exist");
    }
    if ((sr.getOwner() == null) || (sr.getOwner().getId() != getLoggedInCustomer().getId())) {
      throw new PermissionException("You are not the owner of this storage room.");
    }
    box.setStorageRoom(sr);
    box.setOwner(sr.getOwner());
    sr.getBoxes().add(box);
    this.boxRepo.save(box);
  }

  @Override
  @Transactional
  public void removeBox(Long boxId) throws PermissionException, InvalidParameterException {
    Box box = this.boxRepo.findById(boxId).orElse(null);
    if (box == null) {
      throw new InvalidParameterException("Box with the given id does not exist");
    }
    if ((box.getOwner() == null) || (box.getOwner().getId() != getLoggedInCustomer().getId())) {
      throw new PermissionException("You are not the owner of this box. You cannot remove it.");
    }
    this.boxRepo.delete(box);
  }

}
