package com.example.parking.tariffservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "workers",schema = "income_expenses_service")
public final  class CommunalWorker {
    @Id
    @Column(name = "id",updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID cwId;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private double salary;
    @Column(name ="cw_type")
    @Enumerated(EnumType.STRING)
    private CW_Type cwType;
    @Column(name = "iswinter")
    private boolean isWinter;


    public CommunalWorker(UUID cwId, String name, double salary, CW_Type cwType, boolean isWinter, String malfunctionId) {
        this.cwId = cwId;
        this.name = name;
        this.salary = salary;
        this.cwType = cwType;
        this.isWinter = isWinter;

    }

    public CommunalWorker(String name, double salary, CW_Type cwType) {
        this.cwId = UUID.randomUUID();
        this.name = name;
        this.salary = salary;
        this.cwType = cwType;
        this.isWinter = isWinter;

    }

    public CommunalWorker() {

    }

    public UUID getCwId() {
        return cwId;
    }

    public void setCwId(UUID cwId) {
        this.cwId = cwId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public CW_Type getCwType() {
        return cwType;
    }

    public void setCwType(CW_Type cwType) {
        this.cwType = cwType;
    }

    public boolean isWinter() {
        return isWinter;
    }

    public void setWinter(boolean winter) {
        isWinter = winter;
    }



}
