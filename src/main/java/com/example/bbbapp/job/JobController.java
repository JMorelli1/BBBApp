package com.example.bbbapp.job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.*;

import com.example.bbbapp.user.*;
import com.example.bbbapp.contract.*;

@RestController
@RequiredArgsConstructor
public class JobController{

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final Translator translator;

    @GetMapping(path="/jobs/{id}")
    public @ResponseBody JobDTO getJob(@PathVariable Integer id){
        System.out.println(id);
        Job job = jobRepository.findById(id).orElse(null);
        if(job == null){
            System.out.println("Null Job Printed");
            return new JobDTO();
        }
        JobDTO jobDTO = translator.jobToContract(job);
        return jobDTO;
    }

    @GetMapping(path="/jobs")
    public @ResponseBody Iterable<JobDTO> getAllJobs(){
        List<JobDTO> jobs = new ArrayList<>();
        for (Job job: jobRepository.findAll()) {
            jobs.add(translator.jobToContract(job));
        }
        return jobs;
    }
 
    @PostMapping(path="/createpostedjob/{id}")
    public @ResponseBody String createPostedJob(@RequestBody JobDTO newJob, @PathVariable Integer id){
        Job job;
        User user = new User();
        user = userRepository.findById(id).orElse(null);
        job = new Job(newJob.getJobId(),newJob.getDescription(), user, null);
        if(job.getUser() == null){
            return "Can not create job without a valid user ID";
        }
        //Adding the User to the list of Jobs fixes the recursion?? Nope
        // user.addPostedJob(job);
        jobRepository.save(job);
        return "Job created successfully";
    }

    @PutMapping("/assignuser/{userId}")
    public @ResponseBody String assignUser(@RequestBody Integer jobId, @PathVariable Integer userId){
        
        User user = userRepository.findById(userId).orElse(null);
        Job job = jobRepository.findById(jobId).orElse(null);

        if(user == null || job == null){
            return "Error finding Job or User ID";
        }
        user.addAssignedJob(job);
        job.addAssignedUser(user);
        userRepository.save(user);
        jobRepository.save(job);
        
        return "Successfully assigned user";
    }
}
