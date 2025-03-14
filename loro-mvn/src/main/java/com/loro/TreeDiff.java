package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class TreeDiff {
    private List<TreeDiffItem> diff;

    public TreeDiff(
        List<TreeDiffItem> diff
    ) {
        
        this.diff = diff;
    }
    
    public List<TreeDiffItem> diff() {
        return this.diff;
    }
    public void setDiff(List<TreeDiffItem> diff) {
        this.diff = diff;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof TreeDiff) {
            TreeDiff t = (TreeDiff) other;
            return (
              Objects.equals(diff, t.diff)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diff);
    }
}


