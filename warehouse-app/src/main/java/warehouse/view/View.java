package warehouse.view;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.Warehouse;

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
}
