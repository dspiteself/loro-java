package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class ImportStatus {
    private Map<Long, CounterSpan> success;
    private Map<Long, CounterSpan> pending;

    public ImportStatus(
        Map<Long, CounterSpan> success, 
        Map<Long, CounterSpan> pending
    ) {
        
        this.success = success;
        
        this.pending = pending;
    }
    
    public Map<Long, CounterSpan> success() {
        return this.success;
    }
    
    public Map<Long, CounterSpan> pending() {
        return this.pending;
    }
    public void setSuccess(Map<Long, CounterSpan> success) {
        this.success = success;
    }
    public void setPending(Map<Long, CounterSpan> pending) {
        this.pending = pending;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ImportStatus) {
            ImportStatus t = (ImportStatus) other;
            return (
              Objects.equals(success, t.success) && 
              
              Objects.equals(pending, t.pending)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, pending);
    }
}


