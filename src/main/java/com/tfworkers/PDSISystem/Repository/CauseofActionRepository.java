package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Budget;
import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CauseofActionRepository extends JpaRepository<CauseofAction, Long> {
    List<CauseofAction> findByOrderByCreatedDateAsc();
}
