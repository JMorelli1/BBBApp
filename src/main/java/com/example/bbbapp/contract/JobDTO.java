package com.example.bbbapp.contract;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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

}