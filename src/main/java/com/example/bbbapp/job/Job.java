package com.example.bbbapp.job;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.bbbapp.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "JOBS")
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable{

    @Id
    @Column(name = "JOB_ID")
    private Integer idJob;

    @Column(name = "JOB_DESCRIPTION")
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; 

    // @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    // private ArrayList<User> assignedUsers = new ArrayList<>();

}