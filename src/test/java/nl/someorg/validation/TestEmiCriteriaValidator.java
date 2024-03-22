package nl.someorg.validation;

import nl.someorg.model.EmiCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@SpringBootTest()
@TestPropertySource(
        locations = {"classpath:application-test.yaml"}
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestEmiCriteriaValidator {
    @Autowired
    EmiCriteriaValidator validator;

    @Value("${calc.emi.maxYears}")
    private int maxYears;

    @Test
    void testNullInput(){
        try {
            validator.validateEmi(null);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.INPUT_NULL, ex.getMessage());
        }
    }

    @Test
    void testNullValues(){
        EmiCriteria criteria = new EmiCriteria();
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MUST_BE_NUMBER, ex.getMessage());
        }

        criteria.setLoanTermYears(5);
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.YEARLY_INTEREST_MUST_BE_NUMBER, ex.getMessage());
        }

        criteria.setYearlyInterestRate(BigDecimal.valueOf(5.01));
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_VALUE_MUST_BE_NUMBER, ex.getMessage());
        }
    }

    @Test
    void testMinMaxLoanYears(){
        EmiCriteria criteria = new EmiCriteria();
        criteria.setYearlyInterestRate(BigDecimal.valueOf(5.01));
        criteria.setLoanValue(BigDecimal.valueOf(5500));

        criteria.setLoanTermYears(0);
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MIN, ex.getMessage());
        }

        criteria.setLoanTermYears(-1);
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MIN, ex.getMessage());
        }

        criteria.setLoanTermYears(maxYears+1);
        try {
            validator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertTrue(ex.getMessage().contains("Loan term may not be longer than "+maxYears));
            Assertions.assertFalse(ex.getMessage().contains("MAX_YEARS"));
        }
    }
}
