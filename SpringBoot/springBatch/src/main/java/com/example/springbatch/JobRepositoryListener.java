package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author D0Loop
 * @since 2022-07-11 [2022.7월.11]
 */

@Component
@RequiredArgsConstructor
public class JobRepositoryListener implements JobExecutionListener {

    private final JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        JobInstance jobInstance = jobExecution.getJobInstance();

        String jobName = jobInstance.getJobName();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20220710").toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);

        if(lastJobExecution != null) {
            for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {

                String requestDate = stepExecution.getJobExecution().getJobParameters().getString("requestDate");
                System.out.println("requestDate : " + requestDate);

                BatchStatus status = stepExecution.getStatus();
                System.out.println("status : " + status);

                ExitStatus exitStatus = stepExecution.getExitStatus();
                System.out.println("exitStatus : " + exitStatus);

                String stepName = stepExecution.getStepName();
                System.out.println("stepName : " + stepName);
            }
        }

    }
}
