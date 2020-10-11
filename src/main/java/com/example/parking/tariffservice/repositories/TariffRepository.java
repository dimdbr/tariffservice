package com.example.parking.tariffservice.repositories;
import com.example.parking.tariffservice.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff,Integer> {
}
