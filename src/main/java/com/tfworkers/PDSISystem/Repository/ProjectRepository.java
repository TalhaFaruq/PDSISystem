package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{
//    @Query(value = "Select project.name, project.project_id,timeline.timeline_id,timeline.start_date FROM project join project_timelines\n" +
//            "on project.project_id = project_timelines.project_project_id join timeline on timeline.timeline_id = project_timelines\n" +
//            ".timelines_timeline_id where date(timeline.end_date) <= now()")
//    List<Object[]> getProjectTimeline();

//    @Query(value = "Select project.name FROM project p join project_timelines on p.project_id = project_timelines.project_project_id join timeline t on t.timeline_id = project_timelines.timelines_timeline_id where p.project_id = ?1;", nativeQuery = true)
//    List<Object> findProjectName(Long projectId);

}
