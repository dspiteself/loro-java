package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class IdSpan {
    private Long peer;
    private CounterSpan counter;

    public IdSpan(
        Long peer, 
        CounterSpan counter
    ) {
        
        this.peer = peer;
        
        this.counter = counter;
    }
    
    public Long peer() {
        return this.peer;
    }
    
    public CounterSpan counter() {
        return this.counter;
    }
    public void setPeer(Long peer) {
        this.peer = peer;
    }
    public void setCounter(CounterSpan counter) {
        this.counter = counter;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof IdSpan) {
            IdSpan t = (IdSpan) other;
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


