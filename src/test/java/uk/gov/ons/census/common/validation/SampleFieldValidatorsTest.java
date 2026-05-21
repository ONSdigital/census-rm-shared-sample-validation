package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SampleFieldValidatorsTest {

  @Test
  void testSampleFieldValidators() {
    ColumnValidator[] underTest = SampleFieldValidators.getValidators();

    assertThat(underTest).hasSize(27);
    assertThat(underTest[0].getColumnName()).isEqualTo("UPRN");
    Rule[] uprnRules = underTest[0].getRules();
    assertThat(uprnRules).hasSize(3);
    assertThat(uprnRules[0]).isInstanceOf(MandatoryRule.class);
    assertThat(uprnRules[1]).isInstanceOf(LengthRule.class);
    assertThat(uprnRules[2]).isInstanceOf(NumericRule.class);
  }

  @Test
  void testSampleFieldValidatorsAreImmmutable() {
    ColumnValidator[] firstColumnValidators = SampleFieldValidators.getValidators();

    firstColumnValidators[0] = null;

    ColumnValidator[] secondColumnValidators = SampleFieldValidators.getValidators();

    assertThat(secondColumnValidators).isNotNull();
    assertThat(secondColumnValidators[0].getColumnName()).isEqualTo("UPRN");
  }
}
