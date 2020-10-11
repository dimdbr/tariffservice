package com.example.parking.tariffservice.service;


import com.example.parking.tariffservice.model.Tariff;
import javassist.NotFoundException;

import java.util.List;

public interface TariffService {

    Tariff createTariff(Tariff tariff);
    List<Tariff> readAllTariff();

    Tariff readTariff(int id) throws NotFoundException;

    Tariff updateTariff(int id, Tariff tariff) throws NotFoundException;




}
