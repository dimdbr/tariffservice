package com.example.parking.tariffservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "clients",schema = "contract_schema")


public final class Client {
    @Id
    @Column(name = "id",updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private  UUID clientID;
    @Column(name = "name")
    private  String name;
    @Column(name = "monthpay")
    private  double monthPay;


    public Client(String name) {
        this.clientID=UUID.randomUUID();
        this.name = name;
        this.monthPay=0;
    }

    public Client() {

    }

    public UUID getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public double getMonthPay() {
        return monthPay;
    }

    public Client setMonthPay(double monthPay) {
        this.monthPay = monthPay;
        return this;
    }
}
