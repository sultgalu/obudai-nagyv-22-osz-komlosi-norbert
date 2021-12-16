package warehouse.app.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import warehouse.persistence.entity.Material;

public class MaterialsValidator implements ConstraintValidator<MaterialsConstraint, Set<Material>> {

  @Override
  public boolean isValid(Set<Material> value, ConstraintValidatorContext context) {
    if (value.size() == 0) {
      return false;
    }
    return true;
  }

}
