package warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import warehouse.domain.Box;
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

    String uname = "nemenek"; // this.view.readUsername();
    String pwd = "1234"; // this.view.readPassword();
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
    this.service.saveData();
  }

  public void boxesMenu() {
    String input = "-1";
    while (!input.equals("q")) {
      this.view.printBoxesMenu();
      input = this.view.getInput().toLowerCase();
      try {
        switch (input) {
          case "1":
            Box box = this.view.readBox();
            this.view.printReadStorageRoomIdPrompt();
            String srId = this.view.getInput();
            this.service.storeBox(box, Long.valueOf(srId));
            break;
          case "2":
            Long boxId = this.view.selectBoxToRemove(this.service.getLoggedInCustomer());
            this.service.removeBox(boxId);
            break;
          case "3":
            this.view.printCustomerBoxes(this.service.getLoggedInCustomer());
            break;
          case "q":
            return;
          default:
            this.view.printWrongInput();
        }
      } catch (Exception e) {
        this.view.printErrorMessage(e.getLocalizedMessage());
      }

    }
  }

  public void storageRoomsMenu() {
    String input = "-1";
    while (!input.equals("q")) {
      this.view.printStorageRoomsMenu();
      input = this.view.getInput().toLowerCase();
      try {
        switch (input) {
          case "1":
            this.view.printWarehouseStorageRooms(this.service.getWarehouse());
            break;
          case "2":
            this.view.printRentStorageRoomPrompt();
            input = this.view.getInput().toLowerCase();
            this.service.rentStorageRoom(Long.valueOf(input));
            break;
          case "3":
            this.view.printCancelStorageRoomPrompt();
            input = this.view.getInput().toLowerCase();
            this.service.cancelStorageRoomRending(Long.valueOf(input));
            break;
          case "4":
            this.view.printStorageRoomsRentByCustomer(this.service.getLoggedInCustomer());
            break;
          case "q":
            return;
          default:
            this.view.printWrongInput();
        }
      } catch (Exception e) {
        this.view.printErrorMessage(e.getLocalizedMessage());
      }
    }
  }
}
