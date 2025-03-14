package com.loro;


import java.util.List;
import java.util.Map;
public sealed interface ContainerId {
  
  record Root(
    String name, 
    ContainerType containerType) implements ContainerId {
    
  }
  
  record Normal(
    Long peer, 
    Integer counter, 
    ContainerType containerType) implements ContainerId {
    
  }
  
}

