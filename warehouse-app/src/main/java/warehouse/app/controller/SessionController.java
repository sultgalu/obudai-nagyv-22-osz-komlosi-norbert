package warehouse.app.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import warehouse.app.webdomain.LoginRequest;
import warehouse.service.WarehouseService;

@Controller
public class SessionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StorageRoomController.class);
  @Autowired
  private WarehouseService service;

  @PostMapping("/login")
  public String login(Model model, @Valid LoginRequest request, BindingResult bindingResult,
    RedirectAttributes rAttrs) {

    LOGGER.info("ASDDDDD");
    if (!bindingResult.hasErrors()) {
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
