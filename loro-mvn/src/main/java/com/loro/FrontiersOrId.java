package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class FrontiersOrId implements AutoCloseable {
    private Frontiers frontiers;
    private Id id;

    public FrontiersOrId(
        Frontiers frontiers, 
        Id id
    ) {
        
        this.frontiers = frontiers;
        
        this.id = id;
    }
    
    public Frontiers frontiers() {
        return this.frontiers;
    }
    
    public Id id() {
        return this.id;
    }
    public void setFrontiers(Frontiers frontiers) {
        this.frontiers = frontiers;
    }
    public void setId(Id id) {
        this.id = id;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.frontiers, 
        this.id);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof FrontiersOrId) {
            FrontiersOrId t = (FrontiersOrId) other;
            return (
              Objects.equals(frontiers, t.frontiers) && 
              
              Objects.equals(id, t.id)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frontiers, id);
    }
}


