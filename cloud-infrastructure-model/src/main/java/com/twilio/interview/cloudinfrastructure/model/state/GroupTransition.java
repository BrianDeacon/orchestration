package com.twilio.interview.cloudinfrastructure.model.state;

import com.twilio.interview.cloudinfrastructure.model.GroupState;

public class GroupTransition extends Transition<GroupState> {
    public GroupTransition(String name, GroupState startState, GroupState endState, Runnable onStart, Runnable onEnd) {
        super(name, startState, endState, onStart, onEnd);
    }
}
