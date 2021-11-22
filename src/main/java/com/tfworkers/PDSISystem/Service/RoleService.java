package com.tfworkers.PDSISystem.Service;

import com.tfworkers.PDSISystem.Model.Entity.Role;
import com.tfworkers.PDSISystem.Repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * The type Role service.
 */
@Service
public class RoleService {
    private static final Logger log = LogManager.getLogger(RoleService.class);

    /**
     * The Role repository.
     */
    final RoleRepository roleRepository;

    /**
     * Instantiates a new Role service.
     *
     * @param roleRepository the role repository
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private static final Logger logger = LogManager.getLogger(RoleService.class);

    /**
     * Gets all roles.
     *
     * @return the all roles
     */
    public ResponseEntity<Object> getAllRoles() {
        try {
            List<Role> roles = roleRepository.findAllByIsActiveOrderByCreatedDate(true);
            logger.info("Getting role list",roles);
            // check if list is empty or not
            if (roles.isEmpty()) {
                return new ResponseEntity<>("No roles found... ", HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " \n " + e.getCause());
            return new ResponseEntity<>("Role could not fetched...", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Add role response entity.
     *
     * @param role the role
     * @return the response entity
     */
    public ResponseEntity<Object> addRole(Role role) {
        try {
            Calendar date = Calendar.getInstance();
            role.setCreatedDate(date.getTime());
            roleRepository.save(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Role already exist , Duplicates not allowed", HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " \n " + e.getCause());
            return new ResponseEntity<>("Role could not added...", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Gets role by id.
     *
     * @param id the id
     * @return the role by id
     */
    public ResponseEntity<Object> getRoleById(Long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            if (role.isPresent())
                return new ResponseEntity<>(role, HttpStatus.FOUND);
            else
                return new ResponseEntity<>("could not found role , Check id", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(
                    "some error has occurred during fetching Role by id , in class RoleService and its function getRoleById ",
                    e);
            return new ResponseEntity<>("Unable to find Role, an error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /**
     * Update role response entity.
     *
     * @param roles the roles
     * @return the response entity
     */
    public ResponseEntity<Object> updateRole(List<Role> roles) {
        try {
            for (Role role : roles) {
                Calendar date = Calendar.getInstance();

                role.setCreatedDate(date.getTime());
                roleRepository.save(role);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Categories could not be Updated , Data maybe incorrect",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete role response entity.
     *
     * @param id the id
     * @return the response entity
     */
    public ResponseEntity<Object> deleteRole(Long id) {
        try {
            Optional<Role> role = roleRepository.findById(id);
            if (role.isPresent()) {

                // set status false
                role.get().setActive(false);
                // set updated date
                Calendar date = Calendar.getInstance();
                role.get().setUpdatedDate(date.getTime());
                roleRepository.save(role.get());
                return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
            } else
                return new ResponseEntity<>("Role does not exists ", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Role could not be Deleted.......", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
