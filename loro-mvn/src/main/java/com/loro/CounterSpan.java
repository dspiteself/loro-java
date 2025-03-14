package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class CounterSpan {
    private Integer start;
    private Integer end;

    public CounterSpan(
        Integer start, 
        Integer end
    ) {
        
        this.start = start;
        
        this.end = end;
    }
    
    public Integer start() {
        return this.start;
    }
    
    public Integer end() {
        return this.end;
    }
    public void setStart(Integer start) {
        this.start = start;
    }
    public void setEnd(Integer end) {
        this.end = end;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof CounterSpan) {
            CounterSpan t = (CounterSpan) other;
            return (
              Objects.equals(start, t.start) && 
              
              Objects.equals(end, t.end)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}


