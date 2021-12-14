package warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import warehouse.service.WarehouseService;
import warehouse.view.View;

@Component
public class App {
  @Autowired
  private View view;

  @Autowired
  private WarehouseService service;

  public void play() {
    this.service.initializeData();

    this.view.printWelcomeMessage();

    String uname = "Márk"; // this.view.readUsername();
    String pwd = "asd"; // this.view.readPassword();
    this.service.authenticate(uname, pwd);
    if (this.service.isLoggedIn()) {
      this.view.printLoggedIn(uname);
    } else {
      return;
    }

    boolean running = true;
    String input = "-1";
    while (running) {
      this.view.printMainMenu();
      input = this.view.getInput();
      switch (input) {
        case "1":
          storageRoomsMenu();
          break;
        case "2":
          boxesMenu();
          break;
        case "3":
          if (this.view.getLogoutConformation()) {
            this.view.printLoggedOut(uname);
            running = false;
          }
          break;
        default:
          this.view.printWrongInput();
      }
    }

    this.view.printQuitMessage();
  }

  public void boxesMenu() {
    String input = "-1";
    while (!input.equals("q")) {
      this.view.printBoxesMenu();
      input = this.view.getInput().toLowerCase();
      switch (input) {
        case "1":
          this.view.readBox();
          break;
        case "2":
          this.view.selectBoxToRemove(this.service.getLoggedInCustomer());
          break;
        case "3":
          this.view.printCustomerBoxes(this.service.getLoggedInCustomer());
          break;
        case "q":
          return;
        default:
          this.view.printWrongInput();
      }
    }
  }

  public void storageRoomsMenu() {
    String input = "-1";
    while (!input.equals("q")) {
      this.view.printStorageRoomsMenu();
      input = this.view.getInput().toLowerCase();
      switch (input) {
        case "1":
          this.view.printWarehouseStorageRooms(this.service.getWarehouse());
          break;
        case "2":
          this.view.printRentStorageRoomPrompt();
          break;
        case "3":
          this.view.printCancelStorageRoomPrompt();
          break;
        case "4":
          this.view.printStorageRoomsRentByCustomer(this.service.getLoggedInCustomer());
          break;
        case "q":
          return;
        default:
          this.view.printWrongInput();
      }
    }
  }
}
