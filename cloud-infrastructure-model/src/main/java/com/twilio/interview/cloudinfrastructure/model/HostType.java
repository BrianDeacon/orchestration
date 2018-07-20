package com.twilio.interview.cloudinfrastructure.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.String;

/**
 * Interface {@link HostType} defines the contract for the HostType objects.
 */
@JsonSerialize(as=HostType.class)
public interface HostType {
    /**
     * Returns the host (instance) type ID.
     *
     * @return  host type ID
     */
    String getId ();

    /**
     * Returns the host (instance) type.
     *
     * @return  host type
     */
    String getType ();

    /**
     * Returns the host (instance) type description.
     *
     * @return  host type description
     */
    String getDescription ();

    HostSize getSize();
}
