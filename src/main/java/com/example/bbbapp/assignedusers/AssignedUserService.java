package com.example.bbbapp.assignedusers;

import com.example.bbbapp.exception.BusinessException;
import com.example.bbbapp.job.Job;
import com.example.bbbapp.job.JobRepository;
import com.example.bbbapp.user.User;
import com.example.bbbapp.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignedUserService{

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public void assignUser(Integer jobId, Integer userId) throws BusinessException{

        User user = userRepository.findById(userId).orElse(null);
        Job job = jobRepository.findById(jobId).orElse(null);
        if(user == null){
            throw new BusinessException("Error assigning user to job, couldn't find User at ID: " + userId);
        }
        if(job == null){
            throw new BusinessException("Error assigning user to job. couldn't find Job at ID: " + jobId);
        }
        user.addAssignedJob(job);
        userRepository.save(user);
    }

    public void removeAssignedUser(Integer jobId, Integer userId) throws BusinessException{
        User user = userRepository.findById(userId).orElse(null);
        Job job = jobRepository.findById(jobId).orElse(null);
        if(user == null){
            throw new BusinessException("Error removing assigned user to job, couldn't find User at ID: " + userId);
        }
        if(job == null){
            throw new BusinessException("Error removing assigned user to job. couldn't find Job at ID: " + jobId);
        }
        user.removeAssignedJob(job);
        userRepository.save(user);
    }
}