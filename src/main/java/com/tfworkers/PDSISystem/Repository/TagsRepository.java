package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Tags;

import java.util.List;

@Repository
public interface TagsRepository extends JpaRepository<Tags,Long>{
    List<Tags> findAllByIsActiveOrderByCreatedDate(boolean status);
}
