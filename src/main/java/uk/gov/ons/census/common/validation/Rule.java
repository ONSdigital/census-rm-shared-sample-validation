package uk.gov.ons.census.common.validation;

import java.io.Serializable;
import java.util.Optional;

public interface Rule extends Serializable {
  Optional<String> checkValidity(String data);

  default Optional<String> checkValidity(int data) {
    throw new UnsupportedOperationException("This rule is not implemented for integers");
  }

  default Optional<String> checkValidity(boolean data) {
    throw new UnsupportedOperationException("This rule is not implemented for booleans");
  }

  default Optional<String> checkValidity(Object data) {
    throw new UnsupportedOperationException("This rule is not implemented for objects");
  }
}
