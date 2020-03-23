package com.example.bbbapp.contract;

import java.util.ArrayList;
import com.example.bbbapp.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import lombok.Data;

@Data
public class JobDTO{

    @JsonProperty("jobId")
    private Integer jobId;

    @JsonProperty("description")
    private String description;

    @JsonBackReference
    @JsonProperty("postedUser")
    private UserDTO user;

    @JsonBackReference
    @JsonProperty("assignedUsers")
    private List<User> assignedUsers;

    public List<User> getAssignedUsers(){
        if(assignedUsers == null){
            return new ArrayList<>();
        }
        return assignedUsers;
    }

}