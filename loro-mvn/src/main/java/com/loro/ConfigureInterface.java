package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface ConfigureInterface {
    
    public Configure fork();
    
    public Long mergeInterval();
    
    public Boolean recordTimestamp();
    
    public void setMergeInterval(Long interval);
    
    public void setRecordTimestamp(Boolean record);
    
    public StyleConfigMap textStyleConfig();
    
}

