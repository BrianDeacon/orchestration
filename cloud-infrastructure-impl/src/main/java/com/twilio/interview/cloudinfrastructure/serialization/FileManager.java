package com.twilio.interview.cloudinfrastructure.serialization;

import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupType;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostType;

import java.io.File;
import java.util.Collections;
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

    public FileManager(Serializer serializer) {
        this(serializer, new File("."));
    }

    public void saveHostTypes(List<HostType> hostTypes) {
        serializer.write(hostTypes, new File(baseDir, HOST_TYPES_FILE));
    }

    public List<HostType> loadHostTypes() {
        return loadHostTypes(HOST_TYPES_FILE);
    }

    public List<HostType> loadHostTypes(String file) {
        File f = new File(baseDir, file);
        if (!f.exists()) return Collections.emptyList();
        return serializer.readHostTypes(f);
    }

    public void saveHosts(List<Host> hosts) {
        serializer.write(hosts, new File(baseDir, HOSTS_FILE));
    }

    public List<Host> loadHosts() {
        return loadHosts(HOSTS_FILE);
    }

    public List<Host> loadHosts(String file) {
        File f = new File(baseDir, file);
        if (!f.exists()) return Collections.emptyList();
        return serializer.readHosts(f);
    }

    public void saveGroups(List<Group> groups) {
        serializer.write(groups, new File(baseDir, GROUPS_FILE));
    }

    public List<Group> loadGroups() {
        return loadGroups(GROUPS_FILE);
    }

    public List<Group> loadGroups(String file) {
        File f = new File(baseDir, file);
        if (!f.exists()) return Collections.emptyList();
        return serializer.readGroups(f);
    }

    public void saveGroupTypes(List<GroupType> groupTypes) {
        serializer.write(groupTypes, new File(baseDir, GROUP_TYPES_FILE));
    }

    public List<GroupType> loadGroupTypes() {
        return serializer.readGroupTypes(new File(baseDir, GROUP_TYPES_FILE));
    }

    public List<GroupType> loadGroupTypes(String file) {
        File f = new File(baseDir, file);
        if (!f.exists()) return Collections.emptyList();
        return serializer.readGroupTypes(f);
    }


}
