package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class CommitOptions {
    private String origin;
    private Boolean immediateRenew;
    private Long timestamp;
    private String commitMsg;

    public CommitOptions(
        String origin, 
        Boolean immediateRenew, 
        Long timestamp, 
        String commitMsg
    ) {
        
        this.origin = origin;
        
        this.immediateRenew = immediateRenew;
        
        this.timestamp = timestamp;
        
        this.commitMsg = commitMsg;
    }
    
    public String origin() {
        return this.origin;
    }
    
    public Boolean immediateRenew() {
        return this.immediateRenew;
    }
    
    public Long timestamp() {
        return this.timestamp;
    }
    
    public String commitMsg() {
        return this.commitMsg;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setImmediateRenew(Boolean immediateRenew) {
        this.immediateRenew = immediateRenew;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public void setCommitMsg(String commitMsg) {
        this.commitMsg = commitMsg;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof CommitOptions) {
            CommitOptions t = (CommitOptions) other;
            return (
              Objects.equals(origin, t.origin) && 
              
              Objects.equals(immediateRenew, t.immediateRenew) && 
              
              Objects.equals(timestamp, t.timestamp) && 
              
              Objects.equals(commitMsg, t.commitMsg)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, immediateRenew, timestamp, commitMsg);
    }
}


