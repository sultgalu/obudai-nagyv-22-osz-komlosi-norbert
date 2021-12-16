package warehouse.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ExceptionHandler(CustomException.class)
  @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
  public String handleException(Exception ex, Model model) {
    model.addAttribute("errorCode", ex.getMessage()); // errCode
    model.addAttribute("errorMessage", ex.getMessage());
    LOGGER.warn("Handing error: {}", ex.getLocalizedMessage());
    return "app-error";
  }
}
