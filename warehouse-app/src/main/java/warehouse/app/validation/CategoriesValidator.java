package warehouse.app.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import warehouse.persistence.entity.Category;

public class CategoriesValidator implements ConstraintValidator<CategoriesConstraint, Set<Category>> {

  @Override
  public boolean isValid(Set<Category> value, ConstraintValidatorContext context) {
    if (value.size() == 0) {
      return false;
    }
    return true;
  }

}
