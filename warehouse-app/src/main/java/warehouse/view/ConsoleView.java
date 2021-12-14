package warehouse.view;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import warehouse.domain.Box;
import warehouse.domain.Category;
import warehouse.domain.Customer;
import warehouse.domain.Material;
import warehouse.domain.Size;
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
    System.out.print("-- Main menu --\n"
      + "1: Storage Rooms.\n"
      + "2: Boxes.\n"
      + "3: Quit.\n");
  }

  @Override
  public void printStorageRoomsMenu() {
    System.out.print("-- Storage rooms menu --\n"
      + "1: Get information about all storage rooms.\n"
      + "2: Rent a new storage room.\n"
      + "3: Cancel renting.\n"
      + "4: Get information about my storage rooms.\n"
      + "Q: Go back!\n");
  }

  @Override
  public void printBoxesMenu() {
    System.out.print("-- Boxes room menu --\n"
      + "1: New box.\n"
      + "2: Remove box.\n"
      + "3: List my boxes.\n"
      + "Q: Go back!\n");
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
    System.out.print("Size: ");
    String[] size = System.console().readLine().split("x");
    box.size = new Size();
    box.size.x = Integer.valueOf(size[0]);
    box.size.y = Integer.valueOf(size[1]);

    System.out.print("Content: ");
    String content = System.console().readLine();
    box.materials = Arrays.asList(Material.valueOf(content));

    System.out.print("Categories: ");
    List<String> cats = Arrays.asList(System.console().readLine().replace(" ", "").split(","));
    box.categories = Arrays.asList(cats.stream().map(x -> Category.valueOf(x)).toArray(Category[]::new));
    return box;
  }

  @Override
  public void printLoggedIn(String uname) {
    System.out.println("-- " + uname + " is logged in --");
  }

  @Override
  public void printWrongInput() {
    System.out.println("Wrong input!");
  }

  @Override
  public boolean getLogoutConformation() {
    while (true) {
      System.out.println("Are you sure? (Y/N)");
      String input = System.console().readLine().toLowerCase();
      if (input.equals("y")) {
        return true;
      }
      if (input.equals("n")) {
        return false;
      }
      // continue
    }
  }

  @Override
  public void printLoggedOut(String username) {
    System.out.println(username + " user logged out.");
  }

  @Override
  public void printQuitMessage() {
    System.out.println("App has terminated.");
  }

  private static void printStorageRoom(StorageRoom sr) {
    System.out.println("----------------------------");
    System.out.println("StorageRoom Id: " + sr.id);
    System.out.println("\tOwner: " + (sr.owner != null ? sr.owner.username : "nobody"));
    System.out.println("\tSize: " + sr.size.x + "x" + sr.size.y);
    System.out.println("\tBoxes: " + sr.boxes.size());
    System.out.println(sr.isFree ? "Free" : "Not free");
    System.out.println("----------------------------");
  }

  private static void printBox(Box box) {
    String mats = transformToList(box.materials.stream().map(Material::name).toArray());
    String cats = transformToList(box.categories.stream().map(Category::name).toArray());
    System.out.println("----------------------------");
    System.out.println("\tSize: " + box.size.x + "x" + box.size.y);
    System.out.println("\tMaterials: " + mats);
    System.out.println("\tCategories: " + cats);
    System.out.println("----------------------------");
  }

  private static <T> String transformToList(T[] list) {
    String mats = "[";
    for (T m : list) {
      mats += m + " ";
    }
    return mats.trim().replace(" ", ", ") + "]";
  }

}
