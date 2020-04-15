package com.example.bbbapp.contract;

import com.example.bbbapp.user.*;
import com.example.bbbapp.job.*;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Translator {

    public Job jobToEntity(JobDTO jobDTO) {

        Job job = new Job();
        job.setJobId(jobDTO.getJobId());
        job.setJobTitle(jobDTO.getJobTitle());
        job.setDescription(jobDTO.getDescription());
        job.setUser(singleUserToEntity(jobDTO.getUser()));

        Set<User> assignedUsers = new HashSet<>();
        User user;
        for (UserDTO userDTO : jobDTO.getAssignedUsers()) {
            user = singleUserToEntity(userDTO);
            assignedUsers.add(user);
        }
        job.setAssignedUsers(assignedUsers);
        return job;
    }

    public JobDTO jobToContract(Job job) {

        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getJobId());
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setDescription(job.getDescription());

        jobDTO.setUser(singleUserToContract(job.getUser()));

        List<UserDTO> assignedUsers = new ArrayList<>();
        UserDTO userDTO;
        for (User user : job.getAssignedUsers()) {
            userDTO = singleUserToContract(user);
            assignedUsers.add(userDTO);
        }
        jobDTO.setAssignedUsers(assignedUsers);

        return jobDTO;
    }

    public User userToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        Set<Job> postedJobs = new HashSet<>();
        Job postedJob;
        if (userDTO.getPostedJobs() != null) {
            for (JobDTO jobDTO : userDTO.getPostedJobs()) {
                postedJob = jobToEntity(jobDTO);
                postedJobs.add(postedJob);
            }
        }
        Set<Job> assignedJobs = new HashSet<>();
        Job assignedJob = new Job();
        if (userDTO.getPostedJobs() != null) {
            for (JobDTO jobDTO : userDTO.getPostedJobs()) {
                assignedJob = jobToEntity(jobDTO);
                assignedJobs.add(assignedJob);
            }
        }
        user.setAssignedJobs(assignedJobs);
        user.setPostedJobs(postedJobs);
        return user;
    }

    public UserDTO userToContract(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        List<JobDTO> postedJobs = new ArrayList<>();
        JobDTO postedJobDTO;
        for (Job job : user.getPostedJobs()) {
            postedJobDTO = jobToContract(job);
            postedJobs.add(postedJobDTO);
        }
        List<JobDTO> assignedJobs = new ArrayList<>();
        JobDTO assignedJobDTO;
        for (Job job : user.getAssignedJobs()) {
            assignedJobDTO = jobToContract(job);
            assignedJobs.add(assignedJobDTO);
        }
        userDTO.setPostedJobs(postedJobs);
        userDTO.setAssignedJobs(assignedJobs);
        return userDTO;
    }

    private UserDTO singleUserToContract(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    private User singleUserToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return new User();
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}