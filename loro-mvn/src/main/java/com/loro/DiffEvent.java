package com.loro;


import java.util.List;
import java.util.Map;
import java.nio.ByteBuffer;
import java.util.Objects;
public class DiffEvent implements AutoCloseable {
    /**
     * How the event is triggered.
     */
    private EventTriggerKind triggeredBy;
    /**
     * The origin of the event.
     */
    private String origin;
    /**
     * The current receiver of the event.
     */
    private ContainerId currentTarget;
    /**
     * The diffs of the event.
     */
    private List<ContainerDiff> events;

    public DiffEvent(
        EventTriggerKind triggeredBy, 
        String origin, 
        ContainerId currentTarget, 
        List<ContainerDiff> events
    ) {
        
        this.triggeredBy = triggeredBy;
        
        this.origin = origin;
        
        this.currentTarget = currentTarget;
        
        this.events = events;
    }
    
    public EventTriggerKind triggeredBy() {
        return this.triggeredBy;
    }
    
    public String origin() {
        return this.origin;
    }
    
    public ContainerId currentTarget() {
        return this.currentTarget;
    }
    
    public List<ContainerDiff> events() {
        return this.events;
    }
    public void setTriggeredBy(EventTriggerKind triggeredBy) {
        this.triggeredBy = triggeredBy;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setCurrentTarget(ContainerId currentTarget) {
        this.currentTarget = currentTarget;
    }
    public void setEvents(List<ContainerDiff> events) {
        this.events = events;
    }

    
    @Override
    public void close() {
        
    AutoCloseableHelper.close(
        this.triggeredBy, 
        this.origin, 
        this.currentTarget, 
        this.events);
    }
    
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof DiffEvent) {
            DiffEvent t = (DiffEvent) other;
            return (
              Objects.equals(triggeredBy, t.triggeredBy) && 
              
              Objects.equals(origin, t.origin) && 
              
              Objects.equals(currentTarget, t.currentTarget) && 
              
              Objects.equals(events, t.events)
              
            );
        };
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(triggeredBy, origin, currentTarget, events);
    }
}


