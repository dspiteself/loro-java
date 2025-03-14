package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface VersionVectorInterface {
    
    public VersionVectorDiff diff(VersionVector rhs);
    
    public byte[] encode();
    
    public Boolean eq(VersionVector other);
    
    public void extendToIncludeVv(VersionVector other);
    
    public Integer getLast(Long peer);
    
    public List<IdSpan> getMissingSpan(VersionVector target);
    
    public Boolean includesId(Id id);
    
    public Boolean includesVv(VersionVector other);
    
    public CounterSpan intersectSpan(IdSpan target);
    
    public void merge(VersionVector other);
    
    public Ordering partialCmp(VersionVector other);
    
    public void setEnd(Id id);
    
    public void setLast(Id id);
    
}

