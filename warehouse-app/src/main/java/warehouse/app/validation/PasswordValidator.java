package warehouse.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

  private static boolean containsNumber(String value) {
    return value.chars().anyMatch(c -> (c >= '0') && (c <= '9'));
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return (value.length() > 3) && containsNumber(value);
  }

}
