package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class ContainerIdAndDiff implements AutoCloseable {
    private ContainerId cid;
    private Diff diff;

    public ContainerIdAndDiff(
        ContainerId cid, 
        Diff diff
    ) {
        
        this.cid = cid;
        
        this.diff = diff;
    }
    
    public ContainerId cid() {
        return this.cid;
    }
    
    public Diff diff() {
        return this.diff;
    }
    public void setCid(ContainerId cid) {
        this.cid = cid;
    }
    public void setDiff(Diff diff) {
        this.diff = diff;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.cid, 
        this.diff);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ContainerIdAndDiff) {
            ContainerIdAndDiff t = (ContainerIdAndDiff) other;
            return (
              Objects.equals(cid, t.cid) && 
              
              Objects.equals(diff, t.diff)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, diff);
    }
}


