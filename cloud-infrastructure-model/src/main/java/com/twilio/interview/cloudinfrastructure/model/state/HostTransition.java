package com.twilio.interview.cloudinfrastructure.model.state;

import com.twilio.interview.cloudinfrastructure.model.HostState;

public class HostTransition extends Transition<HostState> {

    public HostTransition(String name, HostState startState, HostState endState, Runnable onStart, Runnable onEnd) {
        super(name, startState, endState, onStart, onEnd);
    }

}
