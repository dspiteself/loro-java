package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class CursorWithPos implements AutoCloseable {
    private Cursor cursor;
    private AbsolutePosition pos;

    public CursorWithPos(
        Cursor cursor, 
        AbsolutePosition pos
    ) {
        
        this.cursor = cursor;
        
        this.pos = pos;
    }
    
    public Cursor cursor() {
        return this.cursor;
    }
    
    public AbsolutePosition pos() {
        return this.pos;
    }
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }
    public void setPos(AbsolutePosition pos) {
        this.pos = pos;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.cursor, 
        this.pos);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof CursorWithPos) {
            CursorWithPos t = (CursorWithPos) other;
            return (
              Objects.equals(cursor, t.cursor) && 
              
              Objects.equals(pos, t.pos)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursor, pos);
    }
}


