package com.twilio.interview.cloudinfrastructure.model.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupState;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;

public class GroupImpl implements Group {

    private String id;
    private String type;
    private String description;
    private HostSize size;
    private GroupState state;
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


    @Override
    public void boot() throws IllegalStateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void shutdown() throws IllegalStateException {
        // TODO Auto-generated method stub

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
