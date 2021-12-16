package warehouse.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

import warehouse.app.webdomain.NewBoxRequest;
import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;
import warehouse.persistence.entity.Size;
import warehouse.persistence.entity.StorageRoom;
import warehouse.service.WarehouseService;

@Controller
public class BoxController {
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

  @ModelAttribute("boxes")
  public Iterable<Box> getBoxes() {
    return this.service.getMyBoxes();
  }

  @ModelAttribute("mySrs")
  public Iterable<StorageRoom> getMyStorageRooms() {
    return this.service.getMyStorageRooms();
  }

  @GetMapping("/boxes")
  public String boxes(Model model) {
    model.addAttribute("box", new NewBoxRequest());
    return "boxes";
  }

  @PostMapping("/remove_box/{id}")
  public String removeBox(@PathVariable("id") long id) {

    try {
      this.service.removeBox(id);
    } catch (warehouse.service.PermissionException e) {
      throw new PermissionException(e);
    } catch (warehouse.service.InvalidParameterException e) {
      throw new InvalidParameterException(e);
    }
    LOGGER.info("Box [{}] succesfully removed", id);
    return "redirect:/boxes";
  }

  @PostMapping("/newbox")
  public String newbox(@Valid NewBoxRequest request, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errorMsg = "Validation error" + (bindingResult.getErrorCount() > 1 ? "s" : "") + ":";
      errorMsg += bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
        .collect(Collectors.joining(" | ", "[", "]"));
      throw new InvalidParameterException(errorMsg);
    }

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
    } catch (warehouse.service.PermissionException e) {
      throw new PermissionException(e);
    } catch (warehouse.service.InvalidParameterException e) {
      throw new InvalidParameterException(e);
    }
    LOGGER.info("New box succesfully created");
    return "redirect:boxes";
  }
}
