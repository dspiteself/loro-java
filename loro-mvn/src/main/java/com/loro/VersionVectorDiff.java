package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class VersionVectorDiff {
    /**
     * need to add these spans to move from right to left
     */
    private Map<Long, CounterSpan> retreat;
    /**
     * need to add these spans to move from left to right
     */
    private Map<Long, CounterSpan> forward;

    public VersionVectorDiff(
        Map<Long, CounterSpan> retreat, 
        Map<Long, CounterSpan> forward
    ) {
        
        this.retreat = retreat;
        
        this.forward = forward;
    }
    
    public Map<Long, CounterSpan> retreat() {
        return this.retreat;
    }
    
    public Map<Long, CounterSpan> forward() {
        return this.forward;
    }
    public void setRetreat(Map<Long, CounterSpan> retreat) {
        this.retreat = retreat;
    }
    public void setForward(Map<Long, CounterSpan> forward) {
        this.forward = forward;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof VersionVectorDiff) {
            VersionVectorDiff t = (VersionVectorDiff) other;
            return (
              Objects.equals(retreat, t.retreat) && 
              
              Objects.equals(forward, t.forward)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(retreat, forward);
    }
}


