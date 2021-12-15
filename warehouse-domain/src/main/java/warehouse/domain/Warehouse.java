package warehouse.domain;

import java.util.List;

public class Warehouse {
  private List<Box> boxes;
  private List<Customer> customers;
  private List<StorageRoom> storageRooms;

  public List<Box> getBoxes() {
    return this.boxes;
  }

  public void setBoxes(List<Box> boxes) {
    this.boxes = boxes;
  }

  public List<Customer> getCustomers() {
    return this.customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public List<StorageRoom> getStorageRooms() {
    return this.storageRooms;
  }

  public void setStorageRooms(List<StorageRoom> storageRooms) {
    this.storageRooms = storageRooms;
  }
}
