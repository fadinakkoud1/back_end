package com.book.keeper.model;

import java.util.Date;

public class Payroll {
    private double numOfHours;
    private double hourlyPay;
    private double grossPay;
    private Date checkDate;
    private Date endingPeriodDate;
    private double federalIncomeTax;
    private double socialSecurity;
    private double medicare;
    private double stateIncomeTax;
    private double stateDisabilityInsurance;
    private double netPay;

    public double getNumOfHours() {
        return numOfHours;
    }

    public void setNumOfHours(double numOfHours) {
        this.numOfHours = numOfHours;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getEndingPeriodDate() {
        return endingPeriodDate;
    }

    public void setEndingPeriodDate(Date endingPeriodDate) {
        this.endingPeriodDate = endingPeriodDate;
    }

    public double getFederalIncomeTax() {
        return federalIncomeTax;
    }

    public void setFederalIncomeTax(double federalIncomeTax) {
        this.federalIncomeTax = federalIncomeTax;
    }

    public double getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(double socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public double getMedicare() {
        return medicare;
    }

    public void setMedicare(double medicare) {
        this.medicare = medicare;
    }

    public double getStateIncomeTax() {
        return stateIncomeTax;
    }

    public void setStateIncomeTax(double stateIncomeTax) {
        this.stateIncomeTax = stateIncomeTax;
    }

    public double getStateDisabilityInsurance() {
        return stateDisabilityInsurance;
    }

    public void setStateDisabilityInsurance(double stateDisabilityInsurance) {
        this.stateDisabilityInsurance = stateDisabilityInsurance;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "\n \nPayroll{" +
                "numOfHours=" + numOfHours +
                ",\n hourlyPay=" + hourlyPay +
                ",\n grossPay=" + grossPay +
                ",\n checkDate=" + checkDate +
                ",\n endingPeriodDate=" + endingPeriodDate +
                ",\n federalIncomeTax=" + federalIncomeTax +
                ",\n socialSecurity=" + socialSecurity +
                ",\n medicare=" + medicare +
                ",\n stateIncomeTax=" + stateIncomeTax +
                ",\n stateDisabilityInsurance=" + stateDisabilityInsurance +
                ",\n netPay=" + netPay +
                '}';
    }
}
