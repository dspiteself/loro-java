package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class TreeId {
    private Long peer;
    private Integer counter;

    public TreeId(
        Long peer, 
        Integer counter
    ) {
        
        this.peer = peer;
        
        this.counter = counter;
    }
    
    public Long peer() {
        return this.peer;
    }
    
    public Integer counter() {
        return this.counter;
    }
    public void setPeer(Long peer) {
        this.peer = peer;
    }
    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof TreeId) {
            TreeId t = (TreeId) other;
            return (
              Objects.equals(peer, t.peer) && 
              
              Objects.equals(counter, t.counter)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(peer, counter);
    }
}


