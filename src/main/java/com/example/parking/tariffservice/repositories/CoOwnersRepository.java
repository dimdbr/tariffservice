package com.example.parking.tariffservice.repositories;


import com.example.parking.tariffservice.model.CoOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoOwnersRepository extends JpaRepository<CoOwner,Integer> {
}
