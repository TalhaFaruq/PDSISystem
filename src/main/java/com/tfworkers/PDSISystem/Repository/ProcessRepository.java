package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process,Long>{

}
