package com.example.parking.tariffservice.model;

import javax.persistence.*;

@Entity
@Table(name = "owners",schema = "tariff_schema")
public class CoOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int coOwnerId;
    @Column(name = "name")
    private String name;
    @Column(name = "accountbalance")
    private double accountBalance;

    public CoOwner(int coOwnerId, String name, double accountBalance) {
        this.coOwnerId = coOwnerId;
        this.name = name;
        this.accountBalance = accountBalance;
    }
    public CoOwner(String name, double accountBalance) {

        this.name = name;
        this.accountBalance = accountBalance;
    }
    public CoOwner()
    {}

    public int getCoOwnerId() {
        return coOwnerId;
    }

    public void setCoOwnerId(int coOwnerId) {
        this.coOwnerId = coOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public void addIncome(double income)
    {
        this.accountBalance+=income;
    }
}
