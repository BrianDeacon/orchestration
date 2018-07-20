package com.twilio.interview.cloudinfrastructure.serialization;

import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupType;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostType;

import java.io.File;
import java.util.List;

public class FileManager {

    private final Serializer serializer;
    private final File baseDir;

    private static final String HOSTS_FILE = "hosts.json";
    private static final String HOST_TYPES_FILE = "host-types.json";
    private static final String GROUPS_FILE = "groups.json";
    private static final String GROUP_TYPES_FILE = "group-types.json";

    public FileManager(Serializer serializer, File baseDir) {
        if (!baseDir.isDirectory() || !baseDir.exists()) {
            throw new RuntimeException(String.format("%s does not exist or is not a directory.", baseDir.getAbsolutePath()));
        }
        this.serializer = serializer;
        this.baseDir = baseDir;
    }

    public void saveHostTypes(List<HostType> hostTypes) {
        serializer.write(hostTypes, new File(baseDir, HOST_TYPES_FILE));
    }

    public List<HostType> loadHostTypes() {
        return serializer.readHostTypes(new File(baseDir, HOST_TYPES_FILE));
    }

    public void saveHosts(List<Host> hosts) {
        serializer.write(hosts, new File(baseDir, HOSTS_FILE));
    }

    public List<Host> loadHosts() {
        return serializer.readHosts(new File(baseDir, HOSTS_FILE));
    }

    public void saveGroups(List<Group> groups) {
        serializer.write(groups, new File(baseDir, GROUPS_FILE));
    }

    public List<Group> loadGroups() {
        return serializer.readGroups(new File(baseDir, GROUPS_FILE));
    }

    public void saveGroupTypes(List<GroupType> groupTypes) {
        serializer.write(groupTypes, new File(baseDir, GROUP_TYPES_FILE));
    }

    public List<GroupType> loadGroupTypes() {
        return serializer.readGroupTypes(new File(baseDir, GROUP_TYPES_FILE));
    }


}
