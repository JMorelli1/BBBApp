package com.example.bbbapp.job;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.example.bbbapp.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "JOBS")
@AllArgsConstructor
@NoArgsConstructor
//Lombok doesn't not to create a hash code for the user, this caused the infinite recursion issue
@EqualsAndHashCode(exclude="user")
public class Job implements Serializable{

    @Id
    @Column(name = "JOB_ID")
    private Integer jobId;

    @Column(name = "JOB_DESCRIPTION")
    private String description;

    //Infinite recursions with the JSON call unless told not to reference bi-directionally 
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; 

    // @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // private Set<User> assignedUsers = new HashSet<>();

}