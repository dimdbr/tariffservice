package com.example.parking.tariffservice.repositories;
import com.example.parking.tariffservice.model.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant,Integer> {
}
