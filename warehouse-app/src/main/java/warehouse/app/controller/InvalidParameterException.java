package warehouse.app.controller;

public class InvalidParameterException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidParameterException(String msg) {
    super(msg);
  }

  public InvalidParameterException(Exception e) {
    super(e);
  }
}
