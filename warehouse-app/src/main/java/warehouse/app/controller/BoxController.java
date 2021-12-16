package warehouse.app.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import warehouse.app.webdomain.NewBoxRequest;
import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;
import warehouse.persistence.entity.Size;
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
}
