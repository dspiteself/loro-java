package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class UndoItemMeta implements AutoCloseable {
    private LoroValue value;
    private List<CursorWithPos> cursors;

    public UndoItemMeta(
        LoroValue value, 
        List<CursorWithPos> cursors
    ) {
        
        this.value = value;
        
        this.cursors = cursors;
    }
    
    public LoroValue value() {
        return this.value;
    }
    
    public List<CursorWithPos> cursors() {
        return this.cursors;
    }
    public void setValue(LoroValue value) {
        this.value = value;
    }
    public void setCursors(List<CursorWithPos> cursors) {
        this.cursors = cursors;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.value, 
        this.cursors);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof UndoItemMeta) {
            UndoItemMeta t = (UndoItemMeta) other;
            return (
              Objects.equals(value, t.value) && 
              
              Objects.equals(cursors, t.cursors)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, cursors);
    }
}


