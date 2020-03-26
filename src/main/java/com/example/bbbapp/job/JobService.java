package com.example.bbbapp.job;

import com.example.bbbapp.exception.BusinessException;
import com.example.bbbapp.user.*;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService{

    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public Job getJob(Integer jobId) throws BusinessException{
        Job job = jobRepository.findById(jobId).orElse(null);
        if(job == null){
            throw new BusinessException("Error finding Job with that ID");
        }
        return job;
    }

    public Iterable<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public void updateJob(Job updatedJob, Integer jobId) throws BusinessException{
        Job job = jobRepository.findById(jobId).orElse(null);
        if(job == null){
            throw new BusinessException("Error updating Job at ID: " + jobId);
        }
        job.setDescription(updatedJob.getDescription());
        jobRepository.save(job);
    }

    public void createJob(Job newJob, Integer userId) throws BusinessException{
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new BusinessException("Error finding User with ID: " + userId);
        }
        newJob.setUser(user);
        jobRepository.save(newJob);
    }

    public void deleteJob(Integer jobId) throws BusinessException{
        try{
            jobRepository.deleteById(jobId);
        }
        catch(IllegalArgumentException ie){
            throw new BusinessException("Error deleting job at ID: " + jobId +"\nCould not find Job at that ID");
        }
    }
}