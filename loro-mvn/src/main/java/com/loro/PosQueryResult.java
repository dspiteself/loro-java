package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class PosQueryResult implements AutoCloseable {
    private Cursor update;
    private AbsolutePosition current;

    public PosQueryResult(
        Cursor update, 
        AbsolutePosition current
    ) {
        
        this.update = update;
        
        this.current = current;
    }
    
    public Cursor update() {
        return this.update;
    }
    
    public AbsolutePosition current() {
        return this.current;
    }
    public void setUpdate(Cursor update) {
        this.update = update;
    }
    public void setCurrent(AbsolutePosition current) {
        this.current = current;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.update, 
        this.current);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof PosQueryResult) {
            PosQueryResult t = (PosQueryResult) other;
            return (
              Objects.equals(update, t.update) && 
              
              Objects.equals(current, t.current)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(update, current);
    }
}


