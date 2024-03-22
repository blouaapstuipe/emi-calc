package nl.someorg.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
