package com.example.bbbapp.job;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "ASSIGNED_USERS",
        joinColumns = @JoinColumn(name="JOB_ID"),
        inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> assignedUsers = new HashSet<>();
    
    public void addAssignedUser(User user){
        assignedUsers.add(user);
    }

    public void removeAssignedUser(User user){
        assignedUsers.remove(user);
    }
}