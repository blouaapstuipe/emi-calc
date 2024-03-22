package nl.someorg.model;

public class EmiCriteria {
    private String loanValue;
    private String yearlyInterestRate;
    private String loanTermYears;

    public String getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(String loanValue) {
        this.loanValue = loanValue;
    }

    public String getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(String yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public String getLoanTermYears() {
        return loanTermYears;
    }

    public void setLoanTermYears(String loanTermYears) {
        this.loanTermYears = loanTermYears;
    }
}
