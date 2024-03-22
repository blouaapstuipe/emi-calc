package nl.someorg.calculators;

import nl.someorg.model.CalcResult;
import nl.someorg.model.EmiCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EmiCalculator implements Calculator{
    private final EmiCriteria input;

    private static final Logger logger = LoggerFactory.getLogger(EmiCalculator.class);

    public EmiCalculator(EmiCriteria input){
        this.input = input;
    }

    /**
     * EMI = P x R x (1+R)^N / [(1+R)^N-1]
     * where, “P” is the loan amount, “N” is tenure in months, and “R” is the monthly interest
     * rate.
     */
    @Override
    public CalcResult calculate() {
        CalcResult result = new CalcResult();
        try {
            BigDecimal monthlyInterestRate = input.getYearlyInterestRate().divide(BigDecimal.valueOf(12.00), MathContext.DECIMAL64);
            int tenure = input.getLoanTermYears() * 12;
            BigDecimal product = monthlyInterestRate.add(BigDecimal.valueOf(1)).pow(tenure);
            BigDecimal value = input.getLoanValue()
                    .multiply(monthlyInterestRate)
                    .multiply(product)
                    .divide(product.subtract(BigDecimal.ONE), MathContext.DECIMAL64);
            value = value.setScale(2, RoundingMode.HALF_EVEN);
            result.setValue(value);
            result.setMessage("");
        } catch (ArithmeticException ex){
            logger.error("Exception while calculating EMI", ex);
            throw new CalculatorException("An error has occured while trying to calculate EMI");
        }

        return result;
    }

}
