package com.example.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * @author D0Loop
 * @since 2022-08-02 [2022.8월.02]
 */

public class CustomJobParametersValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {

        if(jobParameters.getString("name") == null) throw new JobParametersInvalidException("name parameters is not found");
    }
}
