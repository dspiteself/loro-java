package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface ListDiffItem extends AutoCloseable  {
  
    /**
     * Insert a new element into the list.
     */
  record Insert(
    List<ValueOrContainer> insert, 
    Boolean isMove) implements ListDiffItem {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.insert, 
        this.isMove);
    }
    
  }
  
    /**
     * Delete n elements from the list at the current index.
     */
  record Delete(
    Integer delete) implements ListDiffItem {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.delete);
    }
    
  }
  
    /**
     * Retain n elements in the list.
     *
     * This is used to keep the current index unchanged.
     */
  record Retain(
    Integer retain) implements ListDiffItem {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.retain);
    }
    
  }
  
}

