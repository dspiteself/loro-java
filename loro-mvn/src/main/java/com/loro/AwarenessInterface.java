package com.loro;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.sun.jna.*;
import com.sun.jna.ptr.*;
public interface AwarenessInterface {
    
    public AwarenessPeerUpdate apply(byte[] encodedPeersInfo);
    
    public byte[] encode(List<Long> peers);
    
    public byte[] encodeAll();
    
    public Map<Long, PeerInfo> getAllStates();
    
    public LoroValue getLocalState();
    
    public Long peer();
    
    public List<Long> removeOutdated();
    
    public void setLocalState(LoroValueLike value);
    
}

