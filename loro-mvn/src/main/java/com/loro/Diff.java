package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface Diff extends AutoCloseable  {
  
  record List(
          java.util.List<ListDiffItem> diff) implements Diff {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.diff);
    }
    
  }
  
  record Text(
          java.util.List<TextDelta> diff) implements Diff {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.diff);
    }
    
  }
  
  record Map(
    MapDelta diff) implements Diff {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.diff);
    }
    
  }
  
  record Tree(
    TreeDiff diff) implements Diff {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.diff);
    }
    
  }
  
  record Counter(
    Double diff) implements Diff {
    
    @Override
    public void close() {
      
    AutoCloseableHelper.close(
        this.diff);
    }
    
  }
  
  record Unknown() implements Diff {
    
    @Override
    public void close() {
      // Nothing to destroy
    }
    
  }
  
  
}

