package com.example.bbbapp.contract;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class UserDTO{

    @JsonProperty("userId")
    private Integer userId; 

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("postedJobs")
    private List<JobDTO> postedJobs;

    public List<JobDTO> getPostedJobs(){
        if(postedJobs == null){
            postedJobs = new ArrayList<>();
        }
        return postedJobs;
    }

    @JsonProperty("assignedJobs")
    private List<JobDTO> assignedJobs;

    public List<JobDTO> getAssignedJobs(){
        if(assignedJobs == null){
            assignedJobs = new ArrayList<>();
        }
        return assignedJobs;
    }

}