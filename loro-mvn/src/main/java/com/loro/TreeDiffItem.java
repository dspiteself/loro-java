package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class TreeDiffItem {
    private TreeId target;
    private TreeExternalDiff action;

    public TreeDiffItem(
        TreeId target, 
        TreeExternalDiff action
    ) {
        
        this.target = target;
        
        this.action = action;
    }
    
    public TreeId target() {
        return this.target;
    }
    
    public TreeExternalDiff action() {
        return this.action;
    }
    public void setTarget(TreeId target) {
        this.target = target;
    }
    public void setAction(TreeExternalDiff action) {
        this.action = action;
    }

    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof TreeDiffItem) {
            TreeDiffItem t = (TreeDiffItem) other;
            return (
              Objects.equals(target, t.target) && 
              
              Objects.equals(action, t.action)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, action);
    }
}


