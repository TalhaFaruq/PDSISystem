package com.tfworkers.PDSISystem.Controller;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.tfworkers.PDSISystem.Model.Entity.Permissions;
import com.tfworkers.PDSISystem.Service.PermissionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The type Permission controller.
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static final String defaultAuthValue = "12345";
    final private PermissionService permissionService;
    //  private static final Logger log = LogManager.getLogger(PermissionService.class);

    /**
     * Instantiates a new Permission controller.
     *
     * @param permissionService the permission service
     */
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
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
     * Gets all permission.
     *
     * @param authValue the auth value
     * @return the all permission
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getAllPermission(@RequestHeader("Authorization") String authValue) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return permissionService.getAllPermission();
            } else {
                return new ResponseEntity<>("SMS: Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Gets permission.
     *
     * @param authValue the auth value
     * @param id        the id
     * @return the permission
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getPermission(@RequestHeader("Authorization") String authValue,
                                                @PathVariable Long id) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return permissionService.getPermissionById(id);
            } else {
                return new ResponseEntity<>("SMS: Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Add permission response entity.
     *
     * @param authValue   the auth value
     * @param permissions the permissions
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addPermission(@RequestHeader("Authorization") String authValue,
                                                @RequestBody List<Permissions> permissions) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return permissionService.savePermission(permissions);
            } else {
                return new ResponseEntity<>("SMS: Not authorize", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Update permission response entity.
     *
     * @param authValue  the auth value
     * @param permission the permission
     * @return the response entity
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updatePermission(@RequestHeader("Authorization") String authValue,
                                                   @RequestBody List<Permissions> permission) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return permissionService.updatePermission(permission);
            } else
                return new ResponseEntity<>("SMS:  not authorize ", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Delete permission response entity.
     *
     * @param authValue the auth value
     * @param id        the id
     * @return the response entity
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@RequestHeader("Authorization") String authValue,
                                                   @PathVariable long id) {
        if (authValue != null) {
            if (authorize(authValue)) {
                return permissionService.deletePermission(id);
            } else
                return new ResponseEntity<>("SMS:  not authorize ", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("Incorrect authorization key ", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Input validation exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> inputValidationException(Exception e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

}