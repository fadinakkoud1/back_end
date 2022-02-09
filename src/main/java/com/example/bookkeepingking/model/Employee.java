package com.example.bookkeepingking.model;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private int ssn;
    private String phoneNumber;
    private String streetNumber;
    private String city;
    private String state;
    private int zipCode;
    private String maritalStatus;
    private int allowances;
    private int numberOfDeductions;
    private String frequencyPay;

    private Payroll payroll;

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getAllowances() {
        return allowances;
    }

    public void setAllowances(int allowances) {
        this.allowances = allowances;
    }

    public int getNumberOfDeductions() {
        return numberOfDeductions;
    }

    public void setNumberOfDeductions(int numberOfDeductions) {
        this.numberOfDeductions = numberOfDeductions;
    }

    public String getFrequencyPay() {
        return frequencyPay;
    }

    public void setFrequencyPay(String frequencyPay) {
        this.frequencyPay = frequencyPay;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "\nfirstName='" + firstName + '\'' +
                ",\n middleName='" + middleName + '\'' +
                ",\n lastName='" + lastName + '\'' +
                ",\n ssn=" + ssn +
                ",\n phoneNumber='" + phoneNumber + '\'' +
                ",\n streetNumber='" + streetNumber + '\'' +
                ",\n city='" + city + '\'' +
                ",\n state='" + state + '\'' +
                ",\n zipCode=" + zipCode +
                ",\n maritalStatus='" + maritalStatus + '\'' +
                ",\n allowances=" + allowances +
                ",\n numberOfDeductions=" + numberOfDeductions +
                ",\n frequencyPay='" + frequencyPay + '\'' +
                ",\n payroll=" + payroll +
                '}';
    }
}
