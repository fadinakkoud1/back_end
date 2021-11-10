package com.book.keeper;

import com.book.keeper.model.Employee;
import com.book.keeper.model.Payroll;
import com.book.keeper.service.TaxService;

import java.util.Date;
import java.text.DecimalFormat;

public class EmployeeDemo {

    static TaxService service = new TaxService();

    public static void main(String[] args) {
        Employee emp = new Employee();
        DecimalFormat formatter = new DecimalFormat("#0.00");

        emp.setFirstName("Fadi");
        emp.setMiddleName("John");
        emp.setLastName("Nakkoud");
        emp.setSsn(666556666);
        emp.setPhoneNumber("(818) 555-5555");
        emp.setStreetNumber("5454 Welcome St");
        emp.setCity("Palmdale");
        emp.setState("California");
        emp.setZipCode(91205);
        emp.setMaritalStatus("Married");
        emp.setAllowances(3);
        emp.setNumberOfDeductions(2);
        emp.setFrequencyPay("Weekly");

        Payroll pay = new Payroll();
        pay.setNumOfHours(65);
        pay.setHourlyPay(15);
        pay.setCheckDate(new Date());
        pay.setEndingPeriodDate(new Date());

        emp.setPayroll(pay);

        TaxService taxService = new TaxService();

        Employee updatedEmployee = taxService.calculateTotalTax(emp);

        System.out.println("Net pay of employee "+emp.getFirstName()+" is "+"$"+formatter.format(updatedEmployee.getPayroll().getNetPay()));
        System.out.println("Total Income Tax after calculations : "+updatedEmployee.getPayroll().getStateIncomeTax());

        System.out.println("\n\n\nEmployee and Payroll detail : \n"+updatedEmployee);


    }
}
