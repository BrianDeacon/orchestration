package com.twilio.interview.cloudinfrastructure.model.impl;

import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostState;

public class HostImpl implements Host {

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

    @Override
    public void boot() throws IllegalStateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate() throws IllegalStateException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() throws IllegalStateException {
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
