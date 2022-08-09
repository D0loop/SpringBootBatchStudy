package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

/**
 * @author D0Loop
 * @since 2022-06-01 [2022.6월.01]
 */

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("Job")
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new CustomTasklet())
                .build();
    }

    @Bean
    public Job helloFlowJob() {
        return jobBuilderFactory.get("helloFlowJob")
                .start(flow())
                .next(helloStep5())
                .end()
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("RUN SPRING BATCH 2");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("helloStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 3");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    
    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowFlowBuilder = new FlowBuilder<>("flowName");
        flowFlowBuilder.start(helloStep3())
                .next(helloStep4())
                .end();

        return flowFlowBuilder.build();
    }

    @Bean
    public Step helloStep3() {
        return stepBuilderFactory.get("helloStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 3");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step helloStep4() {
        return stepBuilderFactory.get("helloStep4")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 4");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step helloStep5() {
        return stepBuilderFactory.get("helloStep5")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================");
                    System.out.println("HELLO SPRING BATCH 5");
                    System.out.println("====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
