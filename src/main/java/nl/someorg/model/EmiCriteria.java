package nl.someorg.model;

import java.math.BigDecimal;

public class EmiCriteria {
    private BigDecimal loanValue;
    private BigDecimal yearlyInterestRate;
    private BigDecimal loanTermYears;

    public BigDecimal getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(BigDecimal loanValue) {
        this.loanValue = loanValue;
    }

    public BigDecimal getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(BigDecimal yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public BigDecimal getLoanTermYears() {
        return loanTermYears;
    }

    public void setLoanTermYears(BigDecimal loanTermYears) {
        this.loanTermYears = loanTermYears;
    }
}
