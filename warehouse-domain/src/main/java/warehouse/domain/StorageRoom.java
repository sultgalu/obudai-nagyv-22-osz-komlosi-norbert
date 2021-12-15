package warehouse.domain;

import java.util.List;

public class StorageRoom {
  private Long id;
  private boolean isFree;
  private Size size;
  private Customer owner;
  private List<Box> boxes;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isFree() {
    return this.isFree;
  }

  public void setFree(boolean isFree) {
    this.isFree = isFree;
  }

  public Size getSize() {
    return this.size;
  }

  public void setSize(Size size) {
    this.size = size;
  }

  public Customer getOwner() {
    return this.owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  public List<Box> getBoxes() {
    return this.boxes;
  }

  public void setBoxes(List<Box> boxes) {
    this.boxes = boxes;
  }
}
