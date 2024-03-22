package nl.someorg.controllers;

import nl.someorg.calculators.EmiCalculator;
import nl.someorg.model.CalcResult;
import nl.someorg.model.EmiCriteria;
import nl.someorg.validation.EmiCriteriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CalcController {

    @Autowired
    private EmiCalculator emiCalculator;

    @Autowired
    private EmiCriteriaValidator emiValidator;

    @PostMapping("/calc/emi")
    CalcResult calcEmi(@RequestBody EmiCriteria input){
        emiValidator.validateEmi(input);
        return emiCalculator.calculate(input);
    } 
}
