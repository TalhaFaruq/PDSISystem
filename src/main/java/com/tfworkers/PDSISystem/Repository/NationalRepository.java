package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.National;

import java.util.List;

@Repository
public interface NationalRepository extends JpaRepository<National,Long>{
    List<National> findAllByIsActiveOrderByCreatedDate(boolean status);
}
