package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class ChangeMeta implements AutoCloseable {
    /**
     * Lamport timestamp of the Change
     */
    private Integer lamport;
    /**
     * The first Op id of the Change
     */
    private Id id;
    /**
     * [Unix time](https://en.wikipedia.org/wiki/Unix_time)
     * It is the number of seconds that have elapsed since 00:00:00 UTC on 1 January 1970.
     */
    private Long timestamp;
    /**
     * The commit message of the change
     */
    private String message;
    /**
     * The dependencies of the first op of the change
     */
    private Frontiers deps;
    /**
     * The total op num inside this change
     */
    private Integer len;

    public ChangeMeta(
        Integer lamport, 
        Id id, 
        Long timestamp, 
        String message, 
        Frontiers deps, 
        Integer len
    ) {
        
        this.lamport = lamport;
        
        this.id = id;
        
        this.timestamp = timestamp;
        
        this.message = message;
        
        this.deps = deps;
        
        this.len = len;
    }
    
    public Integer lamport() {
        return this.lamport;
    }
    
    public Id id() {
        return this.id;
    }
    
    public Long timestamp() {
        return this.timestamp;
    }
    
    public String message() {
        return this.message;
    }
    
    public Frontiers deps() {
        return this.deps;
    }
    
    public Integer len() {
        return this.len;
    }
    public void setLamport(Integer lamport) {
        this.lamport = lamport;
    }
    public void setId(Id id) {
        this.id = id;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setDeps(Frontiers deps) {
        this.deps = deps;
    }
    public void setLen(Integer len) {
        this.len = len;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.lamport, 
        this.id, 
        this.timestamp, 
        this.message, 
        this.deps, 
        this.len);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof ChangeMeta) {
            ChangeMeta t = (ChangeMeta) other;
            return (
              Objects.equals(lamport, t.lamport) && 
              
              Objects.equals(id, t.id) && 
              
              Objects.equals(timestamp, t.timestamp) && 
              
              Objects.equals(message, t.message) && 
              
              Objects.equals(deps, t.deps) && 
              
              Objects.equals(len, t.len)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lamport, id, timestamp, message, deps, len);
    }
}


