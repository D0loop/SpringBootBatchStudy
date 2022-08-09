package com.example.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author D0Loop
 * @since 2022-07-07 [2022.7월.07]
 */

@Component
public class ExecutionContextTasklet1 implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionContextTasklet1.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.debug("step 1 was executed");

        ExecutionContext jobExecutionContext = stepContribution.getStepExecution().getJobExecution().getExecutionContext();
        ExecutionContext stepExecutionContext = stepContribution.getStepExecution().getExecutionContext();

        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();

        if(jobExecutionContext.get("jobName") == null) {
            jobExecutionContext.put("jobName", jobName);
        }

        if(stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName", stepName);
        }

        logger.debug("jobName : {}", jobExecutionContext.get("jobName"));
        logger.debug("stepName : {}", stepExecutionContext.get("stepName"));

        return RepeatStatus.FINISHED;
    }
}
