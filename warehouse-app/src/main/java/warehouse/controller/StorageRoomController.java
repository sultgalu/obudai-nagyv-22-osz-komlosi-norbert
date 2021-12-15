package warehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Size;
import warehouse.service.WarehouseService;
import warehouse.webdomain.LoginRequest;
import warehouse.webdomain.NewBoxRequest;
import warehouse.webdomain.StorageRoomRentRequest;

@Controller
public class StorageRoomController {
  private static final Logger LOGGER = LoggerFactory.getLogger(StorageRoomController.class);
  @Autowired
  private WarehouseService service;

  @GetMapping("/stg")
  public String stg(Model model) {
    model.addAttribute("srs", this.service.getStorageRooms());
    return "stg";
  }

  @RequestMapping("/")
  public String index(Model model) {
    if (this.service.isLoggedIn()) {
      return "redirect:stg";
    }
    return "redirect:login";
  }

  @GetMapping("/mystg")
  public String mystg(Model model) {
    model.addAttribute("srs", this.service.getMyStorageRooms());
    return "mystg";
  }

  @GetMapping("/boxes")
  public String boxes(Model model) {
    model.addAttribute("boxes", this.service.getMyBoxes());
    return "boxes";
  }

  @PostMapping("/newbox")
  public String newbox(NewBoxRequest request) {
    Box box = new Box();
    box.setCategories(request.getCategories());
    box.setMaterials(request.getMaterials());

    Size size = new Size();
    String[] split = request.getSize().split("x");
    size.setX(Integer.valueOf(split[0]));
    size.setY(Integer.valueOf(split[1]));
    box.setSize(size);

    try {
      this.service.storeBox(box, request.getStorageRoomId());
      LOGGER.info("New box succesfully created");
    } catch (Exception e) {
      LOGGER.error("Error during new box: {}", e.getLocalizedMessage());
    }
    return "redirect:boxes";
  }

  @PostMapping("/rent")
  public String rent(StorageRoomRentRequest request) {
    try {
      this.service.rentStorageRoom(request.getId());
      LOGGER.info("Storage room [{}] successfully rented", request.getId());
    } catch (Exception e) {
      LOGGER.error("Storage room [{}] renting failed with error \n {}", request.getId(), e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
    return "redirect:stg";
  }

  @PostMapping("/cancel_rent")
  public String cancelRent(StorageRoomRentRequest request) {
    try {
      this.service.cancelStorageRoomRending(request.getId());
      LOGGER.info("Storage room [{}] renting successfully cancelled", request.getId());
    } catch (Exception e) {
      LOGGER.error("Storage room [{}] renting cancellation failed with error \n {}", request.getId(),
        e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
    return "redirect:mystg";
  }

  @PostMapping("/login")
  public String login(LoginRequest request) {
    try {
      boolean result = this.service.authenticate(request.getUsername(), request.getPassword());
      if (result) {
        LOGGER.info("Succesfully logged in as [{}]", request.getUsername());
        return "redirect:stg";
      } else {
        LOGGER.info("Invalid login attempt as [{}]", request.getUsername());
        return "redirect:login";
      }
    } catch (Exception e) {
      LOGGER.error("Login failed with error \n {}", e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/login")
  public String loginPage(Model model) {
    return "login";
  }
}
