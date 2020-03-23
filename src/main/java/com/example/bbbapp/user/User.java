package com.example.bbbapp.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import com.example.bbbapp.job.Job;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name= "USERS")
public class User{

    @Id
    @Column(name = "USER_ID")
    private Integer idUser;
    
    @Column(name = "USER_FIRST_NAME")
    private String firstName;
    @Column(name = "USER_LAST_NAME")
    private String lastName;
    @Column(name="USER_EMAIL")
    private String email;
    @Column(name="USER_PHONE_NUM")
    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Job> postedJobs = new HashSet<>(0);

    public void addPostedJob(Job job) {
        postedJobs.add(job);
        job.setUser(this);
    }

    public void removePostedJob(Job job) {
        postedJobs.remove(job);
        job.setUser(null);
    } 

    // @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy = "user")
    // private Set<Job> assignedJobs = new HashSet<>(0);
}