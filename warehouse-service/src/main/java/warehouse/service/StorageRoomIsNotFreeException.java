package warehouse.service;

public class StorageRoomIsNotFreeException extends Exception {
  private static final long serialVersionUID = 1L;

  public StorageRoomIsNotFreeException() {
    super();
  }

  public StorageRoomIsNotFreeException(String msg) {
    super(msg);
  }
}
