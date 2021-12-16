package warehouse.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

  private static boolean containsDigit(String value) {
    return value.chars().anyMatch(c -> (c >= '0') && (c <= '9'));
  }

  private static boolean containsLowercaseLetter(String value) {
    return value.chars().anyMatch(c -> (c >= 'a') && (c <= 'z'));
  }

  private static boolean containsUppercaseLetter(String value) {
    return value.chars().anyMatch(c -> (c >= 'A') && (c <= 'Z'));
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return (value.length() > 5)
      && containsDigit(value)
      && containsLowercaseLetter(value)
      && containsUppercaseLetter(value);
  }

}
