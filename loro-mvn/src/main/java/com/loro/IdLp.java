package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class IdLp {
    private Integer lamport;
    private Long peer;

    public IdLp(
        Integer lamport, 
        Long peer
    ) {
        
        this.lamport = lamport;
        
        this.peer = peer;
    }
    
    public Integer lamport() {
        return this.lamport;
    }
    
    public Long peer() {
        return this.peer;
    }
    public void setLamport(Integer lamport) {
        this.lamport = lamport;
    }
    public void setPeer(Long peer) {
        this.peer = peer;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof IdLp) {
            IdLp t = (IdLp) other;
            return (
              Objects.equals(lamport, t.lamport) && 
              
              Objects.equals(peer, t.peer)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lamport, peer);
    }
}


