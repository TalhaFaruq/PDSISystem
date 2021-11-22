package com.tfworkers.PDSISystem.Controller;

import com.tfworkers.PDSISystem.Model.Entity.Role;
import com.tfworkers.PDSISystem.Service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static final String defaultAuthValue = "12345";
    final private RoleService roleService;

    /**
     * Instantiates a new Role controller.
     *
     * @param roleService the role service
     */
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Authorize boolean.
     *
     * @param authValue the auth value
     * @return the boolean
     */
    public Boolean authorize(String authValue) {
        return defaultAuthValue.equals(authValue);
    }

    /**
     * Gets all roles.
     *
     * @param authValue the auth value
     * @return the all roles
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getAllRoles(
            @RequestHeader(required = false, value = "Authorization") String authValue) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return roleService.getAllRoles();
            } else {
                return new ResponseEntity<>("SMS: Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Gets role.
     *
     * @param authValue the auth value
     * @param id        the id
     * @return the role
     * @createdDate 31 -oct-2021
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getRole(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return roleService.getRoleById(id);
            } else {
                return new ResponseEntity<>("SMS: Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Save role response entity.
     *
     * @param authValue the auth value
     * @param role      the role
     * @return the response entity
     * @createdDate 31 -oct-2021
     */
    @PostMapping("/add")
    public ResponseEntity<Object> saveRole(@RequestHeader(required = false, value = "Authorization") String authValue,
                                           @RequestBody Role role) {

        if (authValue != null) {
            if (authorize(authValue)) {
                return roleService.addRole(role);
            } else {
                return new ResponseEntity<>(" Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Update role response entity.
     *
     * @param authValue the auth value
     * @param roles     the roles
     * @return the response entity
     * @createdDate 31 -oct-2021
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateRole(@RequestHeader("Authorization") String authValue,
                                             @RequestBody List<Role> roles) {

        if (authorize(authValue)) {
            return roleService.updateRole(roles);
        } else
            return new ResponseEntity<>("SMS:  not authorize ", HttpStatus.UNAUTHORIZED);
    }

    /**
     * Delete role response entity.
     *
     * @param authValue the auth value
     * @param id        the id
     * @return the response entity
     * @createdDate 31 -oct-2021
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRole(@RequestHeader("Authorization") String authValue, @PathVariable Long id) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return roleService.deleteRole(id);
            } else
                return new ResponseEntity<>("SMS:  not authorize ", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

}