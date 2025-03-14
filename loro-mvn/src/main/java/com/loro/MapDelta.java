package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class MapDelta implements AutoCloseable {
    private Map<String, ValueOrContainer> updated;

    public MapDelta(
        Map<String, ValueOrContainer> updated
    ) {
        
        this.updated = updated;
    }
    
    public Map<String, ValueOrContainer> updated() {
        return this.updated;
    }
    public void setUpdated(Map<String, ValueOrContainer> updated) {
        this.updated = updated;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.updated);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof MapDelta) {
            MapDelta t = (MapDelta) other;
            return (
              Objects.equals(updated, t.updated)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated);
    }
}


