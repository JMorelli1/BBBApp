package com.example.bbbapp.job;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import lombok.RequiredArgsConstructor;

import java.util.*;

import com.example.bbbapp.contract.*;
import com.example.bbbapp.exception.BusinessException;

@RestController
@RequiredArgsConstructor
public class JobController{

    private final JobService jobService;
    private final Translator translator;

    @GetMapping(path="/jobs/{jobId}")
    public ResponseEntity<JobDTO> getJob(@PathVariable Integer jobId) throws BusinessException{
        Job job = jobService.getJob(jobId);
        JobDTO jobDTO = translator.jobToContract(job);
        return ok().body(jobDTO);
    }

    @GetMapping(path="/jobs")
    public @ResponseBody Iterable<JobDTO> getAllJobs(){
        List<JobDTO> jobs = new ArrayList<>();
        for (Job job: jobService.getAllJobs()) {
            jobs.add(translator.jobToContract(job));
        }
        return jobs;
    }
 
    @PutMapping(path="/jobs")
    public ResponseEntity<String> updateJob(@RequestBody JobDTO updatedJob) throws BusinessException{
        Job job = translator.jobToEntity(updatedJob);
        jobService.updateJob(job, job.getJobId());
        return ok().body("Successfully updated job");
    }

    @PostMapping(path="/jobs/{userId}")
    public ResponseEntity<String> createJob(@RequestBody JobDTO newJob, @PathVariable Integer userId) throws BusinessException{
        Job job = translator.jobToEntity(newJob);
        jobService.createJob(job, userId);
        return ok().body("Job successfully created");
    }

    @DeleteMapping(path="/jobs/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Integer jobId) throws BusinessException{
        jobService.deleteJob(jobId);
        return ok().body("Successfully deleted job");
    }


    @PutMapping("/jobs/{userId}/{jobId}")
    public ResponseEntity<String> assignUser(@PathVariable Integer userId, @PathVariable Integer jobId) throws BusinessException{
        jobService.assignUser(jobId, userId);
        return ok().body("Successfully assigned user");
    }
}
