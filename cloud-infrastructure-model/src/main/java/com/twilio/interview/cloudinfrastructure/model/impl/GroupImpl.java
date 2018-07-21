package com.twilio.interview.cloudinfrastructure.model.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupState;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostState;
import com.twilio.interview.cloudinfrastructure.model.state.GroupTransition;
import com.twilio.interview.cloudinfrastructure.model.state.HostTransition;

public class GroupImpl implements Group {

    private Map<GroupState, GroupTransition> transitions = initTransitions();

    private Map<GroupState, GroupTransition> initTransitions() {
        Map<GroupState, GroupTransition> t = new HashMap<>();
        t.put(GroupState.CREATED, new GroupTransition("Creating group", null, GroupState.CREATED, () -> starting(GroupState.CREATED), () -> finishing(GroupState.CREATED)));
        t.put(GroupState.BOOTING, new GroupTransition("Booting group", GroupState.CREATED, GroupState.BOOTING, () -> starting(GroupState.BOOTING), () -> proceedTo(GroupState.RUNNING)));
        t.put(GroupState.RUNNING, new GroupTransition("Running group", GroupState.BOOTING, GroupState.RUNNING, () -> starting(GroupState.RUNNING), () -> finishing(GroupState.RUNNING)));
        t.put(GroupState.SHUTTING_DOWN, new GroupTransition("Shutting down group", GroupState.RUNNING, GroupState.SHUTTING_DOWN, () -> starting(GroupState.SHUTTING_DOWN), () -> proceedTo(GroupState.SHUTDOWN)));
        t.put(GroupState.SHUTDOWN, new GroupTransition("Group shut down", GroupState.SHUTTING_DOWN, GroupState.SHUTDOWN, () -> starting(GroupState.SHUTDOWN), () -> finishing(GroupState.SHUTDOWN)));
        t.put(null, new GroupTransition("Deactivating group", GroupState.SHUTDOWN, null, () -> System.out.println("Deactivating"), () -> System.out.println("Deactivated")));
        return Collections.unmodifiableMap(t);
    }

    private String id;
    private String type;
    private String description;
    private HostSize size;
    private GroupState state;

    public GroupImpl() {
        System.out.println("Group created");
        this.state = GroupState.CREATED;
    }

    @JsonProperty("hosts")
    private List<HostImpl> hosts;

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
    public GroupState getState() {
        return state;
    }

    public void setState(GroupState state) {
        this.state = state;
    }

    @JsonIgnore
    @Override
    public List<Host> getHosts() {
        return hosts.stream().map(i -> {return i;}).collect(Collectors.toList());
    }

    @JsonIgnore
    public void setHosts(List<Host> hosts) {
        this.hosts = hosts.stream().map(HostImpl::new).collect(Collectors.toList());
    }
/*
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
 */
    private void starting(GroupState target) {
        System.out.println(String.format("State transition to %s begun", target.name()));
    }

    private void finishing(GroupState target) {
        System.out.println(String.format("State transition to %s complete", target.name()));
    }

    private void proceedTo(GroupState target) {
        GroupTransition transition = transitions.get(target);
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
        System.out.println("Booting");
        this.state = GroupState.RUNNING;
    }

    @Override
    public void shutdown() throws IllegalStateException {
        System.out.println("Shutting down");
        this.state = GroupState.SHUTDOWN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupImpl group = (GroupImpl) o;

        if (id != null ? !id.equals(group.id) : group.id != null) return false;
        if (type != null ? !type.equals(group.type) : group.type != null) return false;
        if (description != null ? !description.equals(group.description) : group.description != null) return false;
        if (size != group.size) return false;
        if (state != group.state) return false;
        return hosts != null ? hosts.equals(group.hosts) : group.hosts == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (hosts != null ? hosts.hashCode() : 0);
        return result;
    }
}
