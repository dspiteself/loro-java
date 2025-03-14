package com.loro;


import java.util.List;
import java.util.Map;

/**
 * The kind of the event trigger.
 */

public enum EventTriggerKind {
    /**
     * The event is triggered by a local transaction.
     */
  LOCAL,
    /**
     * The event is triggered by importing
     */
  IMPORT,
    /**
     * The event is triggered by checkout
     */
  CHECKOUT;
}


