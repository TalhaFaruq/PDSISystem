package com.tfworkers.PDSISystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tfworkers.PDSISystem.Model.Entity.User;

import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByEmailAndToken(String email, int emailToken);

	@Query(value = "SELECT user.user_id, user.first_name,user.last_name, tags.name FROM user join user_tags on user.user_id=user_tags.user_user_id join tags on tags.tags_id=user_tags.tags_tags_id where tags.name=?1 AND user.post='Manager'",nativeQuery = true)
	List<Object[]> findRecommendedManagers(String tag);

	@Query(value = "SELECT user.user_id, user.first_name,user.last_name, tags.name FROM user join user_tags on user.user_id=user_tags.user_user_id join tags on tags.tags_id=user_tags.tags_tags_id where tags.name=?1 AND user.post='Officer'",nativeQuery = true)
	List<Object[]> findRecommendedOfficers(String tag);

	@Query(value = "delete from user_tags where user_user_id = ?1", nativeQuery = true)
	void delete(Long userId);
}
