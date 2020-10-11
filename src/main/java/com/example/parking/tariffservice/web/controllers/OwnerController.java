package com.example.parking.tariffservice.web.controllers;


import com.example.parking.tariffservice.model.CoOwner;
import com.example.parking.tariffservice.model.Tariff;
import com.example.parking.tariffservice.service.CoOwnerService;
import com.example.parking.tariffservice.service.TariffService;
import javassist.NotFoundException;
import org.hibernate.annotations.Proxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@Proxy(lazy=false)
@RequestMapping(value = "/owners")
@Transactional
public class OwnerController {

    private final CoOwnerService coOwnerService;
    private final TariffService tariffService;

    public OwnerController(CoOwnerService coOwnerService, TariffService tariffService) {
        this.coOwnerService = coOwnerService;
        this.tariffService = tariffService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CoOwner coOwner)
    {
        return ResponseEntity.ok(coOwnerService.createOwner(coOwner));
    }

    @GetMapping()
    public ResponseEntity<?> read()
    {
        return ResponseEntity.ok(coOwnerService.readCoOwner());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) throws NotFoundException
    {
        return ResponseEntity.ok(coOwnerService.readCoOwner(id));
    }


    @PutMapping(value = "/changetariff/{id}")
    public ResponseEntity<?> updateTariff(@PathVariable(name = "id") int tariffid,@RequestBody Tariff tariff) throws NotFoundException
    {
        return ResponseEntity.ok(tariffService.updateTariff(tariffid,tariff));
    }

    @PutMapping(value = "/{id}/collectmoney")
    public ResponseEntity<?> collectMoney(@PathVariable(name = "id") int id) throws NotFoundException
    {
        return ResponseEntity.ok(coOwnerService.collectMoney(id));
    }

    @PutMapping(value = "/paymoney")
    public ResponseEntity<?> payMoney() throws NotFoundException
    {
        coOwnerService.payMoney();

        return ResponseEntity.noContent().build();
    }
}