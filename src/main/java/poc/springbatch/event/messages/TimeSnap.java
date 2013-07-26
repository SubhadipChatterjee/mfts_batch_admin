/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poc.springbatch.event.messages;

import java.util.Date;
import poc.springbatch.types.BatchProcessStatus;

/**
 *
 * @author subhadip.chatterjee@tcs.com
 */
public class TimeSnap {

    private long jobId;
    private Date timestamp;
    private String jobStatus;

    public TimeSnap() {
        timestamp = new Date();
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}
