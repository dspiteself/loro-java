package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface ContainerType {
  
  record Text() implements ContainerType {
    
  }
  
  
  record Map() implements ContainerType {
    
  }
  
  
  record List() implements ContainerType {
    
  }
  
  
  record MovableList() implements ContainerType {
    
  }
  
  
  record Tree() implements ContainerType {
    
  }
  
  
  record Counter() implements ContainerType {
    
  }
  
  
  record Unknown(
    Byte kind) implements ContainerType {
    
  }
  
}

