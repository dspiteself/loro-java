package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface TextDelta {
  
  record Retain(
    Integer retain, 
    Map<String, LoroValue> attributes) implements TextDelta {
    
  }
  
  record Insert(
    String insert, 
    Map<String, LoroValue> attributes) implements TextDelta {
    
  }
  
  record Delete(
    Integer delete) implements TextDelta {
    
  }
  
}

