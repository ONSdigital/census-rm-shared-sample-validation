package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import uk.gov.ons.census.common.model.entity.SampleField;

class ColumnValidatorTest {
  private static final String ERROR_MSG_INCLUDING_DATA =
      "Column 'TREATMENT_CODE' value 'bar' validation error: Not in set of foo";
  private static final String ERROR_MSG_EXCLUDING_DATA =
      "Column 'TREATMENT_CODE' Failed validation for Rule 'InSetRule' validation error: Not in set of foo";

  @Test
  void validateRowSuccess() {
    InSetRule inSetRule = new InSetRule(new String[] {"foo", "bar"});
    ColumnValidator underTest = new ColumnValidator(SampleField.TREATMENT_CODE, new Rule[] {inSetRule});

    Map<String, String> dataRow = Map.of(SampleField.TREATMENT_CODE.toString(), "foo");
    Optional<String> actualValidationResult = underTest.validateRow(dataRow);

    assertThat(actualValidationResult).isEmpty();
  }

  @Test
  void validateRowError() {
    InSetRule inSetRule = new InSetRule(new String[] {"foo"});
    ColumnValidator underTest = new ColumnValidator(SampleField.TREATMENT_CODE, new Rule[] {inSetRule});

    Map<String, String> dataRow = Map.of(SampleField.TREATMENT_CODE.toString(), "bar");
    Optional<String> actualValidationResult = underTest.validateRow(dataRow);

    assertThat(actualValidationResult).isPresent().contains(ERROR_MSG_INCLUDING_DATA);
  }

  @Test
  void validateRowWithDateExcludedErrorMsgsSuccess() {
    InSetRule inSetRule = new InSetRule(new String[] {"foo", "bar"});
    ColumnValidator underTest = new ColumnValidator(SampleField.TREATMENT_CODE, new Rule[] {inSetRule});

    Map<String, String> dataRow = Map.of(SampleField.TREATMENT_CODE.toString(), "foo");
    Optional<String> actualValidationResult = underTest.validateRow(dataRow, true);

    assertThat(actualValidationResult).isEmpty();
  }

  @Test
  void validateRowWithDateExcludedErrorMsgsError() {
    InSetRule inSetRule = new InSetRule(new String[] {"foo"});
    ColumnValidator underTest = new ColumnValidator(SampleField.TREATMENT_CODE, new Rule[] {inSetRule});

    Map<String, String> dataRow = Map.of(SampleField.TREATMENT_CODE.toString(), "bar");
    Optional<String> actualValidationResult = underTest.validateRow(dataRow, true);

    assertThat(actualValidationResult).isPresent().contains(ERROR_MSG_EXCLUDING_DATA);
  }
}
