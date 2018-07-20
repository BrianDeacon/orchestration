package com.twilio.interview.cloudinfrastructure.model.impl;

import java.util.List;

import com.twilio.interview.cloudinfrastructure.model.GroupType;
import com.twilio.interview.cloudinfrastructure.model.HostSize;

public class GroupTypeImpl extends HostTypeImpl implements GroupType {

    private HostSize size;
    private int hostCount;
    private boolean activeOnBoot;
    private List<GroupType> dependencies;

    @Override
    public HostSize getSize() {
        return size;
    }

    public void setSize(HostSize size) {
        this.size = size;
    }

    @Override
    public int getHostCount() {
        return hostCount;
    }

    public void setHostCount(int hostCount) {
        this.hostCount = hostCount;
    }

    @Override
    public boolean isActiveOnBoot() {
        return activeOnBoot;
    }

    public void setActiveOnBoot(boolean activeOnBoot) {
        this.activeOnBoot = activeOnBoot;
    }

    @Override
    public List<GroupType> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<GroupType> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GroupTypeImpl groupType = (GroupTypeImpl) o;

        if (hostCount != groupType.hostCount) return false;
        if (activeOnBoot != groupType.activeOnBoot) return false;
        if (size != groupType.size) return false;
        return dependencies != null ? dependencies.equals(groupType.dependencies) : groupType.dependencies == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + hostCount;
        result = 31 * result + (activeOnBoot ? 1 : 0);
        result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
        return result;
    }
}
