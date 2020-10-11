package com.example.parking.tariffservice.service;


import com.example.parking.tariffservice.model.CoOwner;
import javassist.NotFoundException;

import java.util.List;

public interface CoOwnerService {
    CoOwner createOwner(CoOwner coOwner);
    List<CoOwner> readCoOwner();
    CoOwner readCoOwner(int id) throws NotFoundException;
    CoOwner addToBalance(int id, double incomes) throws NotFoundException;
    CoOwner collectMoney(int id) throws NotFoundException;
    void payMoney();
}
