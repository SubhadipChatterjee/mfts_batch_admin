/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.job.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class SimpleExecutionListener implements JobExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (logger.isInfoEnabled()) {
            logger.info("Before executing the Job instance:" + jobExecution.getJobId());
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            if (logger.isInfoEnabled()) {
                logger.info("Executing the Job instance:" + jobExecution.getJobId() + " is SUCCESSFUL");
            }
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            if (logger.isInfoEnabled()) {
                logger.info("Executing the Job instance:" + jobExecution.getJobId() + " is FAILURE");
            }
        }
    }
}
