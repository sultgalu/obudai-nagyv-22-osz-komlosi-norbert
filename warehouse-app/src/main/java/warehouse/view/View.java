package warehouse.view;

import org.springframework.stereotype.Component;

import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Customer;
import warehouse.persistence.entity.StorageRoom;

@Component
public interface View {
  void printWelcomeMessage();

  String readUsername();

  String readPassword();

  String getInput();

  void printMainMenu();

  void printStorageRoomsMenu();

  void printBoxesMenu();

  void printWarehouseStorageRooms(Iterable<StorageRoom> srs);

  void printStorageRoomsRentByCustomer(Iterable<StorageRoom> srs);

  void printCustomerBoxes(Customer customer);

  Long selectBoxToRemove(Customer customer);

  void printIncorrectCredentialsMessage();

  void printNoRoomMessage();

  void printRentStorageRoomPrompt();

  void printCancelStorageRoomPrompt();

  void printReadStorageRoomIdPrompt();

  Box readBox();

  void printLoggedIn(String uname);

  public void printWrongInput();

  public boolean getLogoutConformation();

  public void printLoggedOut(String username);

  public void printQuitMessage();

  public void printErrorMessage(String msg);

  public void printInfo(String msg);
}
