package com.example.parking.tariffservice.web.controllers;


import com.example.parking.tariffservice.model.Tariff;
import com.example.parking.tariffservice.service.TariffService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tariffs")
public class TariffController {
    @Autowired
    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Tariff tariff)
    {
        return ResponseEntity.ok(tariffService.createTariff(tariff));
    }
    @GetMapping()
    public ResponseEntity<?> read(){
        return ResponseEntity.ok(tariffService.readAllTariff());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") int id) throws NotFoundException
    {
        return ResponseEntity.ok(tariffService.readTariff(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, Tariff tariff) throws NotFoundException
    {
        return ResponseEntity.ok(tariffService.updateTariff(id,tariff));
    }

}
