package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class StyleConfig {
    private ExpandType expand;

    public StyleConfig(
        ExpandType expand
    ) {
        
        this.expand = expand;
    }
    
    public ExpandType expand() {
        return this.expand;
    }
    public void setExpand(ExpandType expand) {
        this.expand = expand;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof StyleConfig) {
            StyleConfig t = (StyleConfig) other;
            return (
              Objects.equals(expand, t.expand)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expand);
    }
}


