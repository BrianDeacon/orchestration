package com.twilio.interview.cloudinfrastructure.model.impl;

import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostState;
import com.twilio.interview.cloudinfrastructure.model.state.HostTransition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HostImpl implements Host {

    private Map<HostState, HostTransition> transitions = initTransitions();

    private Map<HostState, HostTransition> initTransitions() {
        Map<HostState, HostTransition> t = new HashMap<>();
        t.put(HostState.CREATED, new HostTransition("Creating host", null, HostState.CREATED, () -> starting(HostState.CREATED), () -> finishing(HostState.CREATED)));
        t.put(HostState.BOOTING, new HostTransition("Booting host", HostState.CREATED, HostState.BOOTING, () -> starting(HostState.BOOTING), () -> proceedTo(HostState.CONFIGURING)));
        t.put(HostState.CONFIGURING, new HostTransition("Configuring host", HostState.BOOTING, HostState.CONFIGURING, () -> starting(HostState.CONFIGURING), () -> proceedTo(HostState.RUNNING)));
        t.put(HostState.RUNNING, new HostTransition("Running host", HostState.CONFIGURING, HostState.RUNNING, () -> starting(HostState.RUNNING), () -> finishing(HostState.RUNNING)));
        t.put(HostState.SHUTTING_DOWN, new HostTransition("Shutting down host", HostState.RUNNING, HostState.SHUTTING_DOWN, () -> starting(HostState.SHUTTING_DOWN), () -> proceedTo(HostState.SHUTDOWN)));
        t.put(HostState.SHUTDOWN, new HostTransition("Host shut down", HostState.SHUTTING_DOWN, HostState.SHUTDOWN, () -> starting(HostState.SHUTDOWN), () -> finishing(HostState.SHUTDOWN)));
        t.put(null, new HostTransition("Deactivating host", HostState.SHUTDOWN, null, () -> System.out.println("Deactivating"), () -> System.out.println("Deactivated")));
        return Collections.unmodifiableMap(t);
    }



    private String id;
    private String type;
    private String description;
    private HostSize size;
    private HostState state;
    private boolean active;


    public HostImpl() {}
    public HostImpl(Host host) {
        this.id = host.getId();
        this.type = host.getType();
        this.description = host.getDescription();
        this.size = host.getSize();
        this.state = host.getState();
        this.active = host.isActive();
    }

    @Override
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public HostSize getSize() {
        return size;
    }

    public void setSize(HostSize size) {
        this.size = size;
    }

    @Override
    public HostState getState() {
        return state;
    }

    public void setState(HostState state) {
        this.state = state;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void starting(HostState target) {
        System.out.println(String.format("State transition to %s begun", target.name()));
    }

    private void finishing(HostState target) {
        System.out.println(String.format("State transition to %s complete", target.name()));
    }

    private void proceedTo(HostState target) {
        HostTransition transition = transitions.get(target);
        if (transition.getStartState() != this.state) {
            String name = "start";
            if (this.state != null) name = this.state.name();
            throw new IllegalStateException(String.format("Cannot proceed to %s from %s", target.name(), name));
        }
        System.out.println(String.format("Proceeding to %s", target));
        sleep(forAWhile());
        if (transition.getOnStart() != null) {
            transition.getOnStart().run();
        }

        this.state = target;

        if (transition.getOnEnd() != null) {
            new Thread(transition.getOnEnd()).start();
        }
    }

    private void sleep(long howLong) {
        try {
            System.out.println(String.format("It's going to take %d ms", howLong));
            Thread.sleep(howLong);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private long forAWhile() {
        return (long)(new Random().nextDouble() * 3 * 1000);
    }

    @Override
    public void boot() throws IllegalStateException {
        proceedTo(HostState.BOOTING);
    }

    @Override
    public void activate() throws IllegalStateException {
        proceedTo(HostState.CREATED);
        this.setActive(true);
    }

    @Override
    public void deactivate() throws IllegalStateException {
        proceedTo(null);
        this.setActive(false);
    }

    @Override
    public void shutdown() throws IllegalStateException {
        proceedTo(HostState.SHUTTING_DOWN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostImpl host = (HostImpl) o;

        if (active != host.active) return false;
        if (id != null ? !id.equals(host.id) : host.id != null) return false;
        if (type != null ? !type.equals(host.type) : host.type != null) return false;
        if (description != null ? !description.equals(host.description) : host.description != null) return false;
        if (size != host.size) return false;
        return state == host.state;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

}
