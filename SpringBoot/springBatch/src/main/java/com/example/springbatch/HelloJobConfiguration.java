package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author D0Loop
 * @since 2022-06-01 [2022.6월.01]
 */

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .next(helloStep3())
                .incrementer(new RunIdIncrementer())
                .validator(new JobParametersValidator() {
                    @Override
                    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {

                    }
                })
                .preventRestart()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {

                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {

                    }
                })
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 1");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 2");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step helloStep3() {
        return stepBuilderFactory.get("helloStep3")
                .tasklet((contribution, chunkContext) -> {
                    chunkContext.getStepContext().getStepExecution().setStatus(BatchStatus.FAILED);
                    contribution.setExitStatus(ExitStatus.STOPPED);

                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 3");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
