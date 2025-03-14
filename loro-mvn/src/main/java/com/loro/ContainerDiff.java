package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
/**
 * A diff of a container.
 */
public class ContainerDiff implements AutoCloseable {
    /**
     * The target container id of the diff.
     */
    private ContainerId target;
    /**
     * The path of the diff.
     */
    private List<PathItem> path;
    /**
     * Whether the diff is from unknown container.
     */
    private Boolean isUnknown;
    /**
     * The diff
     */
    private Diff diff;

    public ContainerDiff(
        ContainerId target, 
        List<PathItem> path, 
        Boolean isUnknown, 
        Diff diff
    ) {
        
        this.target = target;
        
        this.path = path;
        
        this.isUnknown = isUnknown;
        
        this.diff = diff;
    }
    
    public ContainerId target() {
        return this.target;
    }
    
    public List<PathItem> path() {
        return this.path;
    }
    
    public Boolean isUnknown() {
        return this.isUnknown;
    }
    
    public Diff diff() {
        return this.diff;
    }
    public void setTarget(ContainerId target) {
        this.target = target;
    }
    public void setPath(List<PathItem> path) {
        this.path = path;
    }
    public void setIsUnknown(Boolean isUnknown) {
        this.isUnknown = isUnknown;
    }
    public void setDiff(Diff diff) {
        this.diff = diff;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.target, 
        this.path, 
        this.isUnknown, 
        this.diff);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ContainerDiff) {
            ContainerDiff t = (ContainerDiff) other;
            return (
              Objects.equals(target, t.target) && 
              
              Objects.equals(path, t.path) && 
              
              Objects.equals(isUnknown, t.isUnknown) && 
              
              Objects.equals(diff, t.diff)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, path, isUnknown, diff);
    }
}


