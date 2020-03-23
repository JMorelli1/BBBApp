package com.example.bbbapp.user;

import com.example.bbbapp.contract.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UserController{

    private final UserRepository userRepository;
    private final Translator translator;


    @GetMapping(path="/users/{id}")
    public @ResponseBody UserDTO getUser(@PathVariable Integer id){
        User user = userRepository.findById(id).orElse(null);
        UserDTO userDTO = translator.userToContract(user);
        return userDTO;
    }
 
    @GetMapping(path="/users")
    public @ResponseBody Iterable<UserDTO> getAllUsers(){
        List<UserDTO> users = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            users.add(translator.userToContract(user));
        }
        return users;
    }

    @PostMapping(path="/create")
    public @ResponseBody String addUser(@RequestBody UserDTO newUser){

        User user = new User();
        user = translator.userToEntity(newUser);
        userRepository.save(user);
        return "User successfully created";
    }

    @PutMapping(path="/updateuser/{id}")
    public @ResponseBody String updateUser(@RequestBody User updatedUser, @PathVariable Integer id){

        User user = userRepository.findById(id).orElse(null);
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            userRepository.save(user);
            return "Successfully updated user";
        
    }
}