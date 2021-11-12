package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Timeline;

import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long>{

    List<Timeline> findAllByIsActiveOrderByCreatedDate(boolean status);
}
