package com.example.parking.tariffservice.repositories;


import com.example.parking.tariffservice.model.CommunalWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CommunalWorkerRepository extends JpaRepository<CommunalWorker, UUID> {
}
