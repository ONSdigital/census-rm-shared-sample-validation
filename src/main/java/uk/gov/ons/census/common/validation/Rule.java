package uk.gov.ons.census.common.validation;

import java.io.Serializable;
import java.util.Optional;

public interface Rule extends Serializable {
  Optional<String> checkValidity(String data);

  default Optional<String> checkValidity(int data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for integers");
  }

  default Optional<String> checkValidity(boolean data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for booleans");
  }

  default Optional<String> checkValidity(Boolean data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for Booleans");
  }

  default Optional<String> checkValidity(Object data) {
    if (data == null) {
      // Allow nulls by default
      return Optional.empty();
    }
    // Otherwise the rule can't handle this type of object, throw an exception
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName()
            + " is not implemented for data of Class: "
            + data.getClass().getName()
            + " objects");
  }
}
