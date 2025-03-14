package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface LoroValue {
  
  record Null() implements LoroValue {
    
  }
  
  
  record Bool(
    Boolean value) implements LoroValue {
    
  }
  
  record Double(java.lang.Double value) implements LoroValue {
    
  }
  
  record I64(Long value) implements LoroValue {
    
  }
  
  record Binary(
    byte[] value) implements LoroValue {
    
  }
  
  record String(java.lang.String value) implements LoroValue {
    
  }
  
  record List(
    java.util.List<LoroValue> value) implements LoroValue {
    
  }
  
  record Map(java.util.Map<java.lang.String, LoroValue> value) implements LoroValue {
    
  }
  
  record Container(
    ContainerId value) implements LoroValue {
    
  }
  
}

