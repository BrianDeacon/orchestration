package com.twilio.interview.cloudinfrastructure.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.String;
import java.lang.IllegalStateException;

import java.util.List;

/**
 * Interface {@link Group} defines the behavior of the host group.
 */
@JsonSerialize(as=Group.class)
public interface Group {
    /**
     * Returns the group ID.
     *
     * @return  group ID
     */
    String getId ();

    /**
     * Returns group type.
     *
     * @return  group type
     */

    String getType ();

    /**
     * Returns group description.
     *
     * @return  group description
     */
    String getDescription ();

    /**
     * Returns host (instance) size for the group.
     *
     * @return  group host size
     */
    HostSize getSize ();

    /**
     * Returns current group state.
     *
     * @return  group state
     */
    GroupState getState ();

    /**
     * Returns the list of hosts in a group.
     *
     * @return  group hosts
     */
    List<Host> getHosts ();

    /**
     * Starts booting new group. As the group boots it will go through
     * {@link GroupState.BOOTING} state and end up in {@link GroupState.RUNNING}
     * state when the process is complete. Group boot is complete when all hosts
     * in a group are booted and in {@link HostState.RUNNING} state.
     *
     * @throws  IllegalStateException   current host state does not permit this
     *                                  operation
     */
    void boot () throws IllegalStateException;

    /**
     * Initiates the shut down of the host (instance). As the host is shutting
     * down it will go through {@link HostState.SHUTTING_DOWN} state and end up
     * in {@link HostState.SHUTDOWN} when the process is complete.
     *
     * Note that active hosts (in LB) cannot be shut down. They must be taken
     * out of the load balancer first.
     *
     * @throws  IllegalStateException   current host state does not permit this
     *                                  operation
     */
    void shutdown () throws IllegalStateException;
}
