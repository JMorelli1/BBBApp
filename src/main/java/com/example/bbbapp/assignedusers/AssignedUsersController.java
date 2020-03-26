package com.example.bbbapp.assignedusers;

import com.example.bbbapp.exception.BusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AssignedUsersController{

    private final AssignedUserService assignedUserService;

    @PostMapping("/assignedusers/{userId}/{jobId}")
    public ResponseEntity<String> assignUser(@PathVariable Integer userId, @PathVariable Integer jobId) throws BusinessException{
        assignedUserService.assignUser(jobId, userId);
        return ok().body("Successfully assigned user");
    }

    @DeleteMapping("/assignedusers/{userId}/{jobId}")
    public ResponseEntity<String> removeAssignedUser(@PathVariable Integer userId, @PathVariable Integer jobId) throws BusinessException{
        assignedUserService.removeAssignedUser(jobId, userId);
        return ok().body("Successfully removed assigned user");
    }
}