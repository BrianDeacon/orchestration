package com.twilio.interview.cloudinfrastructure.model.state;

public abstract class Transition<T> {
    protected String name;
    protected T startState;
    protected T endState;
    protected Runnable onStart;
    protected Runnable onEnd;

    public Transition(String name, T startState, T endState, Runnable onStart, Runnable onEnd) {
        this.name = name;
        this.startState = startState;
        this.endState = endState;
        this.onStart = onStart;
        this.onEnd = onEnd;
    }

    public T getStartState() { return startState; }
    public T getEndState() { return endState; }
    public String getName() { return name; }
    public Runnable getOnStart() { return onStart; }
    public Runnable getOnEnd() { return onEnd; }

}
