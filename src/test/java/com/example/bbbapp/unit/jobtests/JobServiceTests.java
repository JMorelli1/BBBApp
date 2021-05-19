package com.example.bbbapp.unit.jobtests;

import com.example.bbbapp.job.JobRepository;
import com.example.bbbapp.job.JobService;
import com.example.bbbapp.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class JobServiceTests {

    @Mock
    private UserRepository mockUserRepo;
    @Mock
    private JobRepository mockJobRepo;

    private JobService jobService;

    @Nested
    @DisplayName("when job is requested with job ID")
    class requestForJobWithId{

        @BeforeEach
        void setup(){

        }
        @Nested
        @DisplayName("when JobId is valid")
        class jobIdValid{
            @BeforeEach
            void setup(){

            }
        }

        @Nested
        @DisplayName("when JobId is invalid")
        class jobIdInvalid{
            @BeforeEach
            void setup(){
            }
        }
    }
}
