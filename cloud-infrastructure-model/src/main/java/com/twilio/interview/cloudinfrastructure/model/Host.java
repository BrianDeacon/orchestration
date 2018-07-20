package com.twilio.interview.cloudinfrastructure.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.String;
import java.lang.IllegalStateException;

/**
 * Interface {@link Host} defines the behavior of the host (instance) objectj.
 */
@JsonSerialize(as=Host.class)
public interface Host {
    /**
     * Returns the host (instance) ID.
     *
     * @return  host ID
     */
    String getId ();

    /**
     * Returns host (instance) type.
     *
     * @return  host type
     */

    String getType ();

    /**
     * Returns host (instance) description.
     *
     * @return  host description
     */
    String getDescription ();

    /**
     * Returns host (instance) size.
     *
     * @return  host size
     */
    HostSize getSize ();

    /**
     * Returns current host (instance) state.
     *
     * @return  host state
     */
    HostState getState ();

    /**
     * Returns current host (instance) load balancer state.
     *
     * @return  a flag indicating whether a host is active (in LB) or inactive
     *          (out of LB)
     */
    boolean isActive ();

    /**
     * Starts booting new host (instance). As the host boots it will go through
     * {@link HostState.BOOTING} and {@link HostState.CONFIGURING} states. When
     * a host is up and running it will transition to {@link HostState.RUNNING}.
     *
     * @throws  IllegalStateException   current host state does not permit this
     *                                  operation
     */
    void boot () throws IllegalStateException;

    /**
     * Activates the host (instance), which will put it into the load balancer.
     *
     * @throws  IllegalStateException   current host state does not permit this
     *                                  operation
     */
    void activate () throws IllegalStateException;

    /**
     * De-activates the host (instance), which will take it out of the load
     * balancer.
     *
     * @throws  IllegalStateException   current host state does not permit this
     *                                  operation
     */
    void deactivate () throws IllegalStateException;

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
