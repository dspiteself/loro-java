package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class UpdateOptions {
    private Double timeoutMs;
    private Boolean useRefinedDiff;

    public UpdateOptions(
        Double timeoutMs, 
        Boolean useRefinedDiff
    ) {
        
        this.timeoutMs = timeoutMs;
        
        this.useRefinedDiff = useRefinedDiff;
    }
    
    public Double timeoutMs() {
        return this.timeoutMs;
    }
    
    public Boolean useRefinedDiff() {
        return this.useRefinedDiff;
    }
    public void setTimeoutMs(Double timeoutMs) {
        this.timeoutMs = timeoutMs;
    }
    public void setUseRefinedDiff(Boolean useRefinedDiff) {
        this.useRefinedDiff = useRefinedDiff;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof UpdateOptions) {
            UpdateOptions t = (UpdateOptions) other;
            return (
              Objects.equals(timeoutMs, t.timeoutMs) && 
              
              Objects.equals(useRefinedDiff, t.useRefinedDiff)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeoutMs, useRefinedDiff);
    }
}


