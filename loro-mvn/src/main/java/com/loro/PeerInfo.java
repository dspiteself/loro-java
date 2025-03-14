package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class PeerInfo {
    private LoroValue state;
    private Integer counter;
    private Long timestamp;

    public PeerInfo(
        LoroValue state, 
        Integer counter, 
        Long timestamp
    ) {
        
        this.state = state;
        
        this.counter = counter;
        
        this.timestamp = timestamp;
    }
    
    public LoroValue state() {
        return this.state;
    }
    
    public Integer counter() {
        return this.counter;
    }
    
    public Long timestamp() {
        return this.timestamp;
    }
    public void setState(LoroValue state) {
        this.state = state;
    }
    public void setCounter(Integer counter) {
        this.counter = counter;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof PeerInfo) {
            PeerInfo t = (PeerInfo) other;
            return (
              Objects.equals(state, t.state) && 
              
              Objects.equals(counter, t.counter) && 
              
              Objects.equals(timestamp, t.timestamp)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, counter, timestamp);
    }
}


