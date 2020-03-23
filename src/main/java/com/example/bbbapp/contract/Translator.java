package com.example.bbbapp.contract;

import com.example.bbbapp.user.*;
import com.example.bbbapp.job.*;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Translator {

    public Job jobToEntity(JobDTO jobDTO){

        Job job = new Job();
        job.setJobId(jobDTO.getJobId());
        System.out.println(job.getJobId());
        job.setDescription(jobDTO.getDescription());
        System.out.println(job.getDescription());
        return job;
    }

    public JobDTO jobToContract(Job job){
        
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getJobId());
        jobDTO.setDescription(job.getDescription());
        return jobDTO;
    }

    public User userToEntity(UserDTO userDTO){
        
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        Set<Job> jobs = new HashSet<>();
        Job job = new Job();
        if(userDTO.getPostedJobs() != null){
        for (JobDTO jobDTO : userDTO.getPostedJobs()) {
                job = jobToEntity(jobDTO);
                jobs.add(job);
            }
        }
        user.setPostedJobs(jobs);
        return user;
    }

    public UserDTO userToContract(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        List<JobDTO> jobs = new ArrayList<>();
        JobDTO jobDTO;
        for (Job job : user.getPostedJobs()) {
            jobDTO = jobToContract(job);
            jobs.add(jobDTO);
        }
        userDTO.setPostedJobs(jobs);
        return userDTO;
    }
} 