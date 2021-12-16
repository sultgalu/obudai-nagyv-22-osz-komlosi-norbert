package warehouse.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidator implements ConstraintValidator<SizeConstraint, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      String[] split = value.split("x");
      if (split.length != 2) {
        return false;
      }
      Long.valueOf(split[0]);
      Long.valueOf(split[1]);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

}
