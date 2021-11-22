package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByIsActiveOrderByCreatedDate(boolean Status);
}
