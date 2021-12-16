package warehouse.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;
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

  @ModelAttribute("materials")
  public List<Material> getMaterials() {
    return Arrays.asList(Material.values());
  }

  @ModelAttribute("categories")
  public List<Category> getCategories() {
    return Arrays.asList(Category.values());
  }

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
    return "redirect:stg";
  }

  @GetMapping("/mystg")
  public String mystg(Model model) {
    model.addAttribute("srs", this.service.getMyStorageRooms());
    return "mystg";
  }

  @GetMapping("/boxes")
  public String boxes(Model model) {
    model.addAttribute("boxes", this.service.getMyBoxes());
    model.addAttribute("box", new NewBoxRequest());
    return "boxes";
  }

  @GetMapping("/remove_box/{id}")
  public String removeBox(@PathVariable("id") long id) {
    try {
      this.service.removeBox(id);
      LOGGER.info("Box [{}] succesfully removed", id);
    } catch (Exception e) {
      LOGGER.error("Box [{}] remove failed with error \n {}", id, e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
    return "redirect:/boxes";
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

  @PostMapping("/cancel_rent/{id}")
  public String cancelRent(@PathVariable long id) {
    try {
      this.service.cancelStorageRoomRending(id);
      LOGGER.info("Storage room [{}] renting successfully cancelled", id);
    } catch (Exception e) {
      LOGGER.error("Storage room [{}] renting cancellation failed with error \n {}", id,
        e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
    return "redirect:/mystg";
  }

  @PostMapping("/login")
  public String login(Model model, @Valid LoginRequest request, BindingResult bindingResult,
    RedirectAttributes rAttrs) {

    LOGGER.info("ASDDDDD");
    if (bindingResult.hasErrors()) {
      rAttrs.addFlashAttribute("message", "FAILED");
      throw new CustomException("ASDASD");
      // return "redirect:login";
    }

    try {
      boolean result = true; // this.service.authenticate(request.getUsername(), request.getPassword());
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
  public String loginPage() {
    return "login";
  }
}
