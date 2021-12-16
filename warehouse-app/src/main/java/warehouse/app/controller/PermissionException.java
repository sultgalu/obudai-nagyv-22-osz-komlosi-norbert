package warehouse.app.controller;

public class PermissionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PermissionException(String msg) {
    super(msg);
  }

  public PermissionException(Exception e) {
    super(e);
  }
}
