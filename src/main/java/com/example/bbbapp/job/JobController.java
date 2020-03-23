package com.example.bbbapp.job;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        System.out.println('2');
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
 
    @PostMapping(path="/createjob/{id}")
    public @ResponseBody String createJob(@RequestBody JobDTO newJob, @PathVariable Integer id){
        Job job;
        User user = new User();
        user = userRepository.findById(id).orElse(null);
        job = new Job(newJob.getId(),newJob.getDescription(), user);
        if(job.getUser() == null){
            return "Can not create job without a valid user ID";
        }
        user.addPostedJob(job);
        jobRepository.save(job);
        return "Job created successfully";
    }
}
