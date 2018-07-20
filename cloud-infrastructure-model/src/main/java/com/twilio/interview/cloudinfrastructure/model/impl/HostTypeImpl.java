package com.twilio.interview.cloudinfrastructure.model.impl;

import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostType;

public class HostTypeImpl implements HostType {
    private String id;
    private String type;
    private String description;
    private HostSize size;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostTypeImpl hostType = (HostTypeImpl) o;

        if (id != null ? !id.equals(hostType.id) : hostType.id != null) return false;
        if (type != null ? !type.equals(hostType.type) : hostType.type != null) return false;
        if (description != null ? !description.equals(hostType.description) : hostType.description != null)
            return false;
        return size == hostType.size;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}
