package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauseofActionRepository extends JpaRepository<CauseofAction, Long> {
    List<CauseofAction> findAllByIsActiveOrderByCreatedDate(boolean status);
}
