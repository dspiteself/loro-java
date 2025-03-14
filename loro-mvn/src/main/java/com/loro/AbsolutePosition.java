package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class AbsolutePosition {
    private Integer pos;
    private Side side;

    public AbsolutePosition(
        Integer pos, 
        Side side
    ) {
        
        this.pos = pos;
        
        this.side = side;
    }
    
    public Integer pos() {
        return this.pos;
    }
    
    public Side side() {
        return this.side;
    }
    public void setPos(Integer pos) {
        this.pos = pos;
    }
    public void setSide(Side side) {
        this.side = side;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof AbsolutePosition) {
            AbsolutePosition t = (AbsolutePosition) other;
            return (
              Objects.equals(pos, t.pos) && 
              
              Objects.equals(side, t.side)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, side);
    }
}


