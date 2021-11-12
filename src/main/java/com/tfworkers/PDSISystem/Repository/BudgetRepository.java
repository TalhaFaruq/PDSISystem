package com.tfworkers.PDSISystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Budget;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{
    List<Budget> findAllByIsActiveOrderByCreatedDate(boolean status);

}
