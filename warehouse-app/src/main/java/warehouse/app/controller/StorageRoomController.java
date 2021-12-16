package warehouse.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import warehouse.app.webdomain.StorageRoomRentRequest;
import warehouse.service.WarehouseService;

@Controller
public class StorageRoomController {
  private static final Logger LOGGER = LoggerFactory.getLogger(StorageRoomController.class);
  @Autowired
  private WarehouseService service;

  @RequestMapping("/")
  public String index(Model model) {
    if (this.service.isLoggedIn()) {
      return "redirect:stg";
    }
    return "redirect:stg";
  }

  @GetMapping("/stg")
  public String stg(Model model) {
    model.addAttribute("srs", this.service.getStorageRooms());
    return "stg";
  }

  @GetMapping("/mystg")
  public String mystg(Model model) {
    model.addAttribute("srs", this.service.getMyStorageRooms());
    return "mystg";
  }

  @PostMapping("/rent")
  public String rent(StorageRoomRentRequest request) {
    try {
      this.service.rentStorageRoom(request.getId());
    } catch (warehouse.service.PermissionException e) {
      throw new PermissionException(e);
    } catch (warehouse.service.InvalidParameterException e) {
      throw new InvalidParameterException(e);
    }
    LOGGER.info("Storage room [{}] successfully rented", request.getId());
    return "redirect:stg";
  }

  @PostMapping("/cancel_rent/{id}")
  public String cancelRent(@PathVariable long id) {
    try {
      this.service.cancelStorageRoomRending(id);
    } catch (warehouse.service.PermissionException e) {
      throw new PermissionException(e);
    } catch (warehouse.service.InvalidParameterException e) {
      throw new InvalidParameterException(e);
    }
    LOGGER.info("Storage room [{}] renting successfully cancelled", id);

    return "redirect:/mystg";
  }

}
