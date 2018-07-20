package com.twilio.interview.cloudinfrastructure.model;

/**
 * Enumeration {@link GroupState} defines the states a host in cloud
 * infrastructure transitions through.
 */
public enum GroupState {
    CREATED (null),
    BOOTING (CREATED),
    RUNNING (BOOTING),
    SHUTTING_DOWN(RUNNING),
    SHUTDOWN(SHUTTING_DOWN);

    private GroupState priorState;

    private GroupState(GroupState priorState) {
        this.priorState = priorState;
    }

    public GroupState getPriorState() {
        return priorState;
    }
}
