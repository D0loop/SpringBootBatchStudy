package com.example.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * @author D0Loop
 * @since 2022-07-07 [2022.7월.07]
 */

@Component
public class ExecutionContextTasklet4 implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(Tasklet.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.debug("step 4 was executed");

        ExecutionContext jobExecutionContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        Object name = jobExecutionContext.get("name");
        logger.debug("name = {}", name);

        return RepeatStatus.FINISHED;
    }
}
