package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.CauseofAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.International;

import java.util.List;

@Repository

public interface InternationalRepository extends JpaRepository<International,Long>{
    List<International> findAllByIsActiveOrderByCreatedDate(boolean status);

}
