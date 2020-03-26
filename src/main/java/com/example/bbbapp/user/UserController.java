package com.example.bbbapp.user;

import com.example.bbbapp.contract.*;
import com.example.bbbapp.exception.BusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UserController{

    private final Translator translator;
    private final UserService userService;


    @GetMapping(path="/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) throws BusinessException{
        User user = userService.getUser(userId);
        UserDTO userDTO = translator.userToContract(user);
        return ok().body(userDTO);
    }
 
    @GetMapping(path="/users")
    public @ResponseBody Iterable<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        for (User user: userService.getAllUsers()) {
            users.add(translator.userToContract(user));
        }
        return users;
    }

    @PostMapping(path="/users")
    public @ResponseBody String addUser(@RequestBody UserDTO newUser){

        User user = new User();
        user = translator.userToEntity(newUser);
        userService.addUser(user);
        return "User successfully created";
    }

    @PutMapping(path="/users/{id}")
    public @ResponseBody String updateUser(@RequestBody UserDTO updatedUser, @PathVariable Integer userId){

        User user = translator.userToEntity(updatedUser);
        userService.updateUser(userId, user);
        return "Successfully updated user";
        
    }
}