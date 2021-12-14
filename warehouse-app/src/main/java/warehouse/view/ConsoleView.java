package warehouse.view;

import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Customer;
import warehouse.domain.StorageRoom;
import warehouse.domain.Warehouse;

@Component
public class ConsoleView implements View {

  @Override
  public void printWelcomeMessage() {
    System.out.println("-- Welcome to the Warehouse Application! --");
  }

  @Override
  public String readUsername() {
    System.console().printf("Enter username: ", "");
    return System.console().readLine();
  }

  @Override
  public String readPassword() {
    System.console().printf("Enter password: ", "");
    return new String(System.console().readPassword());
  }

  @Override
  public String getInput() {
    return System.console().readLine();
  }

  @Override
  public void printMainMenu() {
    System.out.print("Főmenü!");
  }

  @Override
  public void printStorageRoomsMenu() {
    System.out.print("Storage room!");

  }

  @Override
  public void printBoxesMenu() {
    System.out.print("Boxes room!");
  }

  @Override
  public void printWarehouseStorageRooms(Warehouse warehouse) {
    warehouse.storageRooms.stream().forEach(sr -> {
      printStorageRoom(sr);
    });
  }

  @Override
  public void printStorageRoomsRentByCustomer(Customer customer) {
    customer.storageRooms.stream().forEach(sr -> {
      printStorageRoom(sr);
    });
  }

  @Override
  public void printCustomerBoxes(Customer customer) {
    customer.storageRooms.stream().forEach(sr -> {
      sr.boxes.stream().forEach(box -> {
        printBox(box);
      });
    });
  }

  @Override
  public Long selectBoxToRemove(Customer customer) {
    printCustomerBoxes(customer);
    System.out.print("Give the ID of the box you want to remove \r\n"
      + "from the storage room:");
    return Long.parseLong(getInput());
  }

  @Override
  public void printIncorrectCredentialsMessage() {
    System.out.println("Incorrect username or password. Please try again!");
  }

  @Override
  public void printNoRoomMessage() {
    System.out.println("No more rooms available");
  }

  @Override
  public void printRentStorageRoomPrompt() {
    System.out.println("Enter the ID of the storage room you want to rent!");
  }

  @Override
  public void printCancelStorageRoomPrompt() {
    System.out.println("Enter the ID of the storage room you want to stop renting!");
  }

  @Override
  public void printReadStorageRoomIdPrompt() {
    System.out.println("Give the ID of the storage room you want to place the box:");
  }

  @Override
  public Box readBox() {
    Box box = new Box();
    System.out.print("box Id: ");
    box.id = Long.valueOf(System.console().readLine());

    return box;
  }

  private static void printStorageRoom(StorageRoom sr) {
    System.out.println(sr.id);
    // System.out.println("StorageRoom Id: " + sr.id);
    // System.out.println("\tOwner: " + sr.owner.username);
    // System.out.println("\tSize: " + sr.size);
    // System.out.println("\tBoxes: " + sr.boxes.size());
    // System.out.println(sr.isFree ? "Free" : "Not free");
  }

  private static void printBox(Box box) {
    System.out.println(box.id);
  }

}
