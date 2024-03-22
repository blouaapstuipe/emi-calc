package nl.someorg.validation;

import nl.someorg.model.EmiCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestEmiCriteriaValidator {
    @Test
    void testNullInput(){
        try {
            EmiCriteriaValidator.validateEmi(null);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.INPUT_NULL, ex.getMessage());
        }
    }

    @Test
    void testNullValues(){
        EmiCriteria criteria = new EmiCriteria();
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MUST_BE_NUMBER, ex.getMessage());
        }

        criteria.setLoanTermYears(5);
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.YEARLY_INTEREST_MUST_BE_NUMBER, ex.getMessage());
        }

        criteria.setYearlyInterestRate(BigDecimal.valueOf(5.01));
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_VALUE_MUST_BE_NUMBER, ex.getMessage());
        }
    }

    @Test
    void testLoanAmount(){

    }

    @Test
    void testMinMaxLoanYears(){
        EmiCriteria criteria = new EmiCriteria();
        criteria.setYearlyInterestRate(BigDecimal.valueOf(5.01));
        criteria.setLoanValue(BigDecimal.valueOf(5500));

        criteria.setLoanTermYears(0);
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MIN, ex.getMessage());
        }

        criteria.setLoanTermYears(-1);
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MIN, ex.getMessage());
        }

        criteria.setLoanTermYears(31);
        try {
            EmiCriteriaValidator.validateEmi(criteria);
            Assertions.fail();
        } catch (CalcValidationException ex){
            Assertions.assertEquals(EmiCriteriaValidator.LOAN_TERM_YEARS_MAX, ex.getMessage());
        }
    }
}
