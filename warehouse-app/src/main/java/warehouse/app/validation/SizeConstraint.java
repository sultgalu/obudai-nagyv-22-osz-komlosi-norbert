package warehouse.app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = SizeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SizeConstraint {
  String message() default "Size is not in a valid format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
