package warehouse.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WarehouseExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseExceptionHandler.class);

  @ExceptionHandler(InvalidParameterException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public String handleInvalidParameterException(Exception ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    model.addAttribute("errorCode", 400);
    LOGGER.warn("Handing error: {}", ex.getLocalizedMessage());
    return "app-error";
  }

  @ExceptionHandler(PermissionException.class)
  @ResponseStatus(code = HttpStatus.FORBIDDEN)
  public String handlePermissionException(Exception ex, Model model) {
    model.addAttribute("errorMessage", ex.getMessage());
    model.addAttribute("errorCode", 403);
    LOGGER.warn("Handing error: {}", ex.getLocalizedMessage());
    return "app-error";
  }
}
