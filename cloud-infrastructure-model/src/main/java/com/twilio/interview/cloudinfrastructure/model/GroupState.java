package com.twilio.interview.cloudinfrastructure.model;

/**
 * Enumeration {@link GroupState} defines the states a host in cloud
 * infrastructure transitions through.
 */
public enum GroupState {
    CREATED,
    BOOTING,
    RUNNING,
    SHUTTING_DOWN,
    SHUTDOWN
}
