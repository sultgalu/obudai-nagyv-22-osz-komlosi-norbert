package warehouse.view;

import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.Warehouse;

@Component
public interface View {
  void printWelcomeMessage();

  String readUsername();

  String readPassword();

  String getInput();

  void printMainMenu();

  void printStorageRoomsMenu();

  void printBoxesMenu();

  void printWarehouseStorageRooms(Warehouse warehouse);

  void printStorageRoomsRentByCustomer(Customer customer);

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
