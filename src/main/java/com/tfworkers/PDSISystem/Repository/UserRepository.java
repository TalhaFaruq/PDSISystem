package com.tfworkers.PDSISystem.Repository;

import com.tfworkers.PDSISystem.Model.Entity.DTO.RecommendedManagerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByEmailAndToken(String email, int emailToken);

	@Query(value = "SELECT user.user_id, user.first_name, user.department FROM user JOIN tags ON tags.name = \"Administrative\" and user.post = \"Manager\"",nativeQuery = true)
	List<RecommendedManagerDTO> recommendedManagers(String tagName, String manager);

}
