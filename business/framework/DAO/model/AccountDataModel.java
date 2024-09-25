package edu.mum.cs.cs525.labs.exercises.project.business.framework.DAO.model;

public class AccountDataModel {
    private String accountNum;
    private String accountName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String accountType;
    private String accountStatus;
    private Double amount;
    private String email;

    public AccountDataModel(String accountNum, String accountName, String street, String city, String state, String zip, String accountType, String amount, String email) {
        this.accountNum = accountNum;
        this.accountName = accountName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.accountType = accountType;
        this.amount = Double.parseDouble(amount);
        this.email = email;
    }
}
