package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface Index {
  
  record Key(
    String key) implements Index {
    
  }
  
  record Seq(
    Integer index) implements Index {
    
  }
  
  record Node(
    TreeId target) implements Index {
    
  }
  
}

