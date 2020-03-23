package com.example.bbbapp.contract;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JobDTO implements Serializable{

    @JsonProperty("jobId")
    private Integer id;

    @JsonProperty("description")
    private String description;

    @JsonBackReference
    @JsonProperty("postedUser")
    private UserDTO user;

}