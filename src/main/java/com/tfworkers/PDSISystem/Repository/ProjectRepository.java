package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.Project;

import java.lang.reflect.Array;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>{
    List<Project> findByProjectStatus(boolean projectStatus);
    List<Project>findByOrderByCreatedDateAsc();

//    @Query(value = "Select project.name, project.project_id,timeline.timeline_id,timeline.start_date FROM project join project_timelines\n" +
//            "on project.project_id = project_timelines.project_project_id join timeline on timeline.timeline_id = project_timelines\n" +
//            ".timelines_timeline_id where date(timeline.end_date) <= now()")
//    List<Object[]> getProjectTimeline();

    @Query(value = "SELECT user.email FROM user join user_projects on user.user_id=user_projects.user_user_id join project on project.project_id=user_projects.projects_project_id where project.name=?1", nativeQuery = true)
    List<Object[]> findByProject_id(Long projectId);

}
