package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class AwarenessPeerUpdate {
    private List<Long> updated;
    private List<Long> added;

    public AwarenessPeerUpdate(
        List<Long> updated, 
        List<Long> added
    ) {
        
        this.updated = updated;
        
        this.added = added;
    }
    
    public List<Long> updated() {
        return this.updated;
    }
    
    public List<Long> added() {
        return this.added;
    }
    public void setUpdated(List<Long> updated) {
        this.updated = updated;
    }
    public void setAdded(List<Long> added) {
        this.added = added;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof AwarenessPeerUpdate) {
            AwarenessPeerUpdate t = (AwarenessPeerUpdate) other;
            return (
              Objects.equals(updated, t.updated) && 
              
              Objects.equals(added, t.added)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated, added);
    }
}


