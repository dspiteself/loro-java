package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class PathItem {
    private ContainerId container;
    private Index index;

    public PathItem(
        ContainerId container, 
        Index index
    ) {
        
        this.container = container;
        
        this.index = index;
    }
    
    public ContainerId container() {
        return this.container;
    }
    
    public Index index() {
        return this.index;
    }
    public void setContainer(ContainerId container) {
        this.container = container;
    }
    public void setIndex(Index index) {
        this.index = index;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof PathItem) {
            PathItem t = (PathItem) other;
            return (
              Objects.equals(container, t.container) && 
              
              Objects.equals(index, t.index)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(container, index);
    }
}


