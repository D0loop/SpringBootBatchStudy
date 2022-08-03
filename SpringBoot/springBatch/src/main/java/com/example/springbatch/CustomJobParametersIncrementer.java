package com.example.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author D0Loop
 * @since 2022-08-03 [2022.8월.03]
 */

public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-hhmmss");

    @Override
    public JobParameters getNext(JobParameters jobParameters) {

        String id = SIMPLE_DATE_FORMAT.format(new Date());

        return new JobParametersBuilder().addString("run.id", id).toJobParameters();
    }
}
