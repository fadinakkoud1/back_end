package com.book.keeper.service;

import com.book.keeper.database.DatabaseUtil;
import com.book.keeper.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {
    private static String FEDERAL_BIWEEKLY_SINGLE = "federal_biweekly_single";
    private static String FEDERAL_WEEKLY_MARRIED = "federal_weekly_married";
    private static String FEDERAL_BIWEEKLY_MARRIED = "federal_biweekly_married";
    private static String FEDERAL_WEEKLY_SINGLE = "federal_weekly_single";

    public Employee calculateTotalTax(Employee employee) {
        double gross = employee.getPayroll().getHourlyPay() * employee.getPayroll().getNumOfHours();
        employee.getPayroll().setGrossPay(gross);
        double fit = calculateFederalDeduction(employee);
        double ssn = gross * 0.062;
        double medicare = gross * 0.0145;
        double sdi = gross * 0.01;

        calculateStateDeductions(employee);

        double netPay = gross - fit - ssn - medicare - sdi - employee.getPayroll().getStateIncomeTax();

        employee.getPayroll().setFederalIncomeTax(fit);
        employee.getPayroll().setSocialSecurity(ssn);
        employee.getPayroll().setMedicare(medicare);
        employee.getPayroll().setStateDisabilityInsurance(sdi);
        employee.getPayroll().setNetPay(netPay);

        return employee;
    }

    public double calculateFederalDeduction(Employee employee) {
        String frequencyPay = employee.getFrequencyPay();
        String tableName = "federal";
        if(frequencyPay.equalsIgnoreCase("biweekly")) {
            tableName +="_biweekly";
        } else {
            tableName +="_weekly";
        }
        if(employee.getMaritalStatus().equalsIgnoreCase("Married")) {
            tableName +="_married";
        } else {
            tableName +="_single";
        }

        return DatabaseUtil.findFederalIncomeTax(tableName, employee.getPayroll().getGrossPay(), employee.getAllowances());
    }

    public Employee calculateStateDeductions(Employee employee) {
        Double stateLowIncomeExemption = fetchDataFromStateLowIncomeExemption(employee);
        if(employee.getPayroll()!=null && employee.getPayroll().getGrossPay() > stateLowIncomeExemption) {
            double standardDeduction = fetchDataFromStandardDeduction(employee);
            double remainingGross = employee.getPayroll().getGrossPay() - standardDeduction;
            //employee.getPayroll().setGrossPay(remainingGross);

            List<Double> taxRateFields = fetchDataStateTaxRate(employee, remainingGross);
            remainingGross = remainingGross - taxRateFields.get(1);
            remainingGross = remainingGross * (taxRateFields.get(0)/100);
            remainingGross = remainingGross + taxRateFields.get(2);

            double stateExemptionAllowance = fetchDataFromStateExemptionAllowance(employee);
            double totalStateTaxes = remainingGross - stateExemptionAllowance;
            employee.getPayroll().setStateIncomeTax(totalStateTaxes);

        } else {
            employee.getPayroll().setStateIncomeTax(0);
        }
        return employee;
    }

    private double fetchDataFromStateLowIncomeExemption(Employee employee) {
        String query = createQueryForLowIncomeExemption(employee);
        Double stateLowIncomeExemption = DatabaseUtil.findAmountFromTableByQuery(query);
        return stateLowIncomeExemption;
    }

    private String createQueryForLowIncomeExemption(Employee employee) {
        String selectQuery = "select ";
        if(employee.getMaritalStatus().equalsIgnoreCase("Single")) {
            selectQuery += "single from state_low_income_exemption where payroll_period = ";
            if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
                selectQuery += "weekly";
            } else {
                selectQuery += "biweekly";
            }
        } else {
            int allowances = employee.getAllowances();
            if(allowances<2) {
                selectQuery += "married_0_1_allowances ";
            } else {
                selectQuery += "married_2_more_allowances ";
            }
            selectQuery += "from state_low_income_exemption where payroll_period = ";
            if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
                selectQuery += "'weekly' ";
            } else {
                selectQuery += "'biweekly' ";
            }
        }
        return selectQuery;
    }

    private double fetchDataFromStandardDeduction(Employee employee) {
        String query = createQueryForStandardDeduction(employee);
        Double standardDeduction = DatabaseUtil.findAmountFromTableByQuery(query);
        return standardDeduction;
    }

    private String createQueryForStandardDeduction(Employee employee) {
        String selectQuery = "select ";
        if(employee.getMaritalStatus().equalsIgnoreCase("Single")) {
            selectQuery += "single from standard_deduction where payroll_period = ";
            if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
                selectQuery += "weekly";
            } else {
                selectQuery += "biweekly";
            }
        } else {
            int allowances = employee.getAllowances();
            if(allowances<2) {
                selectQuery += "married_0_1_allowances ";
            } else {
                selectQuery += "married_2_more_allowances ";
            }
            selectQuery += "from standard_deduction where payroll_period = ";
            if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
                selectQuery += "'weekly' ";
            } else {
                selectQuery += "'biweekly' ";
            }
        }
        return selectQuery;
    }

    private List<Double> fetchDataStateTaxRate(Employee employee, double remainingGross) {
        String query = createQueryForTaxRate(employee, remainingGross);
        List<Double> taxRateFields = DatabaseUtil.findFieldsFromTaxRate(query);
        return taxRateFields;
    }

    private String createQueryForTaxRate(Employee employee, double remainingGross) {
        String selectQuery = "select percentage, amount_minus, amount_plus from ";
        selectQuery += "state_tax_rate_";
        if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
            selectQuery += "weekly_";
        } else {
            selectQuery += "biweekly_";
        }
        if(employee.getMaritalStatus().equalsIgnoreCase("Single")) {
            selectQuery += "single";
        } else {
            selectQuery += "married";
        }

        selectQuery += " where income_over <"+remainingGross+" and but_not_over > "+ remainingGross;
        return selectQuery;
    }

    private double fetchDataFromStateExemptionAllowance(Employee employee) {
        String query = createQueryStateExemptionAllowance(employee);
        Double standardDeduction = DatabaseUtil.findAmountFromTableByQuery(query);
        return standardDeduction;
    }

    private String createQueryStateExemptionAllowance(Employee employee) {
        String selectQuery = "select ";
        if(employee.getFrequencyPay().equalsIgnoreCase("WEEKLY")) {
            selectQuery += "weekly_payroll_period";
        } else {
            selectQuery += "biweekly_payroll_period";
        }
        selectQuery += " from state_exemption_allowance where withholdings_allowances = " + employee.getAllowances();

        return selectQuery;
    }
}
