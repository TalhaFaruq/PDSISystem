package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.FuturePlan;

import java.util.List;

@Repository
public interface FuturePlanRepository extends JpaRepository<FuturePlan, Long>{
    List<FuturePlan> findAllByIsActiveOrderByCreatedDate(boolean status);
}
