package nl.someorg.validation;

import nl.someorg.model.EmiCriteria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EmiCriteriaValidator {

    @Value("${calc.emi.maxYears}")
    private int maxYears;

    public static String INPUT_NULL = "input is null";
    public static String LOAN_TERM_YEARS_MUST_BE_NUMBER = "Loan term years must have a number value";
    public static String YEARLY_INTEREST_MUST_BE_NUMBER = "Yeary interest rate must have a number value";
    public static String LOAN_VALUE_MUST_BE_NUMBER = "Loan value must have a number value";
    public static String LOAN_VALUE_MUST_BE_POSITIVE = "Loan value must have a positive value";
    public static String INTEREST_RATE_POSITIVE = "Interest rate may not be negative";
    public static String INTEREST_RATE_MAX = "Interest rate may not be larger than 100";
    public static String LOAN_TERM_YEARS_MIN = "Loan term years may not be zero or negative";
    public static String LOAN_TERM_YEARS_MAX = "Loan term may not be longer than MAX_YEARS years";

    public void validateEmi(EmiCriteria input) {
        if (input == null) {
            throw new CalcValidationException(INPUT_NULL);
        }
        if (input.getLoanTermYears() == null) {
            throw new CalcValidationException(LOAN_TERM_YEARS_MUST_BE_NUMBER);
        }
        if (input.getYearlyInterestRate() == null) {
            throw new CalcValidationException(YEARLY_INTEREST_MUST_BE_NUMBER);
        }
        if (input.getLoanValue() == null) {
            throw new CalcValidationException(LOAN_VALUE_MUST_BE_NUMBER);
        }
        if (input.getLoanValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new CalcValidationException(LOAN_VALUE_MUST_BE_POSITIVE);
        }
        if (input.getYearlyInterestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new CalcValidationException(INTEREST_RATE_POSITIVE);
        }
        if (input.getYearlyInterestRate().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new CalcValidationException(INTEREST_RATE_MAX);
        }
        if (input.getLoanTermYears() <= 0) {
            throw new CalcValidationException(LOAN_TERM_YEARS_MIN);
        }
        if (input.getLoanTermYears() > maxYears) {
            String message = LOAN_TERM_YEARS_MAX.replace("MAX_YEARS", maxYears+"");
            throw new CalcValidationException(message);
        }
    }

}
