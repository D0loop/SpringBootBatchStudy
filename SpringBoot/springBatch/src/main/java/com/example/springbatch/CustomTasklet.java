package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * @author D0Loop
 * @since 2022-07-03 [2022.7월.03]
 */

@Component
public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        System.out.println("====================");
        System.out.println("HELLO SPRING BATCH 1");
        System.out.println("====================");

        System.out.println("Step1 was executed by customTasklet");

        return RepeatStatus.FINISHED;
    }
}
