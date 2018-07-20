package com.twilio.interview.cloudinfrastructure.model;

/**
 * Enumeration {@link HostState} defines the states a host in cloud
 * infrastructure transitions through.
 */
public enum HostState {
    CREATED,
    BOOTING,
    CONFIGURING,
    RUNNING,
    SHUTTING_DOWN,
    SHUTDOWN
}
