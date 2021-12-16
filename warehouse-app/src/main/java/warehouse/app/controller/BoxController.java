package warehouse.app.controller;

import java.util.ArrayList;
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

import warehouse.app.transform.BoxTransformer;
import warehouse.app.webdomain.BoxView;
import warehouse.app.webdomain.NewBoxRequest;
import warehouse.persistence.entity.Box;
import warehouse.persistence.entity.Category;
import warehouse.persistence.entity.Material;
import warehouse.service.WarehouseService;

@Controller
public class BoxController {
  private static final Logger LOGGER = LoggerFactory.getLogger(StorageRoomController.class);
  @Autowired
  private WarehouseService service;
  @Autowired
  private BoxTransformer boxTransformer;

  @ModelAttribute("materials")
  public List<Material> getMaterials() {
    return Arrays.asList(Material.values());
  }

  @ModelAttribute("categories")
  public List<Category> getCategories() {
    return Arrays.asList(Category.values());
  }

  @ModelAttribute("boxes")
  public Iterable<BoxView> getBoxes() {
    ArrayList<BoxView> result = new ArrayList<>();
    this.service.getMyBoxes().forEach(x -> {
      BoxView boxView = new BoxView();
      this.boxTransformer.transform(boxView, x);
      result.add(boxView);
    });
    return result;
  }

  @ModelAttribute("mySrsId")
  public Iterable<Long> getMyStorageRooms() {
    ArrayList<Long> result = new ArrayList<>();
    this.service.getMyStorageRooms().forEach(x -> result.add(x.getId()));
    return result;
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
    this.boxTransformer.transform(box, request);

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
