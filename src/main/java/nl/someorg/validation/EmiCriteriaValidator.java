package nl.someorg.validation;

import nl.someorg.model.EmiCriteria;

public class EmiCriteriaValidator {
    public static void validateEmi(EmiCriteria input){
        if (input == null){
            throw new CalcValidationException("input is null");
        }
        if (input.getLoanTermYears() == null){
            throw new CalcValidationException("input.loanTermYears must have a number value");
        }
        if (input.getYearlyInterestRate() == null){
            throw new CalcValidationException("input.yearlyInterestRate must have a number value");
        }
        if (input.getLoanValue() == null){
            throw new CalcValidationException("input.loanValue must have a number value");
        }
    }

}
