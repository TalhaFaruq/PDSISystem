package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{

}
