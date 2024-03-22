package nl.someorg.validation;

import nl.someorg.model.EmiCriteria;

import java.math.BigDecimal;

public class EmiCriteriaValidator {
    public static void validateEmi(EmiCriteria input){
        if (input == null){
            throw new CalcValidationException("input is null");
        }
        if (input.getLoanTermYears() == null){
            throw new CalcValidationException("Loan term years must have a number value");
        }
        if (input.getYearlyInterestRate() == null){
            throw new CalcValidationException("Yeary interest rate must have a number value");
        }
        if (input.getLoanValue() == null){
            throw new CalcValidationException("Loan value must have a number value");
        }
        if (input.getLoanValue().compareTo(BigDecimal.ZERO) < 0){
            throw new CalcValidationException("Loan value must have a positive value");
        }
        if (input.getYearlyInterestRate().compareTo(BigDecimal.ZERO) < 0){
            throw new CalcValidationException("Interest rate may not be negative");
        }
        if (input.getYearlyInterestRate().compareTo(BigDecimal.valueOf(100)) > 0){
            throw new CalcValidationException("Interest rate may not larger than 100");
        }
        if (input.getLoanTermYears() <= 0){
            throw new CalcValidationException("Loan term years may not be negative");
        }
        if (input.getLoanTermYears() > 30){
            throw new CalcValidationException("Loan term may not be longer than 30 years");
        }
    }

}
