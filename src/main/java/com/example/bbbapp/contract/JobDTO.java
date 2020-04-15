package com.example.bbbapp.contract;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import lombok.Data;

@Data
public class JobDTO {

    @JsonProperty("jobId")
    private Integer jobId;

    @JsonProperty("jobTitle")
    private String jobTitle;

    @JsonProperty("description")
    private String description;

    @JsonProperty("postedUser")
    private UserDTO user;

    @JsonProperty("assignedUsers")
    private List<UserDTO> assignedUsers;

    public List<UserDTO> getAssignedUsers() {
        if (assignedUsers == null) {
            return new ArrayList<>();
        }
        return assignedUsers;
    }

}