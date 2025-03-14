package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class ContainerPath {
    private ContainerId id;
    private Index path;

    public ContainerPath(
        ContainerId id, 
        Index path
    ) {
        
        this.id = id;
        
        this.path = path;
    }
    
    public ContainerId id() {
        return this.id;
    }
    
    public Index path() {
        return this.path;
    }
    public void setId(ContainerId id) {
        this.id = id;
    }
    public void setPath(Index path) {
        this.path = path;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ContainerPath) {
            ContainerPath t = (ContainerPath) other;
            return (
              Objects.equals(id, t.id) && 
              
              Objects.equals(path, t.path)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }
}


