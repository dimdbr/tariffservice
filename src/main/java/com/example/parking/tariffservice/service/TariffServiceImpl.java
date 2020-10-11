package com.example.parking.tariffservice.service;


import com.example.parking.tariffservice.model.Tariff;
import com.example.parking.tariffservice.repositories.TariffRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    @Override
    public Tariff createTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @Override
    public List<Tariff> readAllTariff() {
        return tariffRepository.findAll();
    }

    @Override
    public Tariff readTariff(int id) throws NotFoundException {
        Optional<Tariff> tempTariff= tariffRepository.findById(id);
        if(tempTariff.isPresent())
            return tariffRepository.findById(id).get();
        else
            throw new NotFoundException(String.format("Tariff with id %s dont exist",id));
    }

    @Override
    public Tariff updateTariff(int id, Tariff tariff) throws NotFoundException {
        if(tariffRepository.existsById(id));
            tariff.setTariffId(id);
        return tariffRepository.save(tariff);
    }
}
