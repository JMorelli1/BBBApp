package com.example.bbbapp.job;

import com.example.bbbapp.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "JOBS")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_ID")
    private Integer jobId;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "JOB_DESCRIPTION")
    private String description;

    // Infinite recursions with the JSON call unless told not to reference
    // bi-directionally
    @JsonBackReference(value = "posted_job")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "ASSIGNED_USERS", joinColumns = @JoinColumn(name = "JOB_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> assignedUsers = new HashSet<>();

    public Job(Integer jobId, String jobTitle, String description, User user, Set<User> assignedUsers) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.description = description;
        this.user = user;
        this.assignedUsers = assignedUsers;
    }

    public Job() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Job;
    }

}