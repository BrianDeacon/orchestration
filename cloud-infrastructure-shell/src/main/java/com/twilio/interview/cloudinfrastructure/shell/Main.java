package com.twilio.interview.cloudinfrastructure.shell;

import asg.cliche.Command;
import asg.cliche.ShellFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupType;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostType;
import com.twilio.interview.cloudinfrastructure.serialization.FileManager;
import com.twilio.interview.cloudinfrastructure.serialization.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    private FileManager fileManager;
    private Serializer serializer;
    private List<HostType> hostTypes;
    private List<Host> hosts;
    private List<Group> groups;
    private List<GroupType> groupTypes;

    @Command
    public String print(String which) {
        Object toPrint = null;
        if (which.equalsIgnoreCase("host-types")) toPrint = hostTypes;
        if (which.equalsIgnoreCase("hosts")) toPrint = hosts;
        if (which.equalsIgnoreCase("groups")) toPrint = groups;
        if (which.equalsIgnoreCase("group-types")) toPrint = groupTypes;
        return serializer.write(toPrint);
    }

    @Command
    public void loadGroupTypes(String file) {
        this.groupTypes = fileManager.loadGroupTypes(file);
    }

    @Command
    public void loadGroupTypes() {
        this.groupTypes = fileManager.loadGroupTypes();
    }

    @Command
    public void loadHosts() {
        this.hosts = fileManager.loadHosts();
    }

    @Command
    public void loadHosts(String file) {
        this.hosts = fileManager.loadHosts(file);
    }

    @Command
    public void loadGroups() {
        this.groups = fileManager.loadGroups();
    }

    @Command
    public void loadGroups(String file) {
        this.groups = fileManager.loadGroups(file);
    }

    @Command
    public void loadAll() {
        loadHostTypes();
        loadGroupTypes();
        loadHosts();
        loadGroups();
    }

    @Command
    public void loadHostTypes() {
        System.out.println("Loading types");
        this.hostTypes = fileManager.loadHostTypes();
    }

    @Command
    public void loadHostTypes(String file) {
        System.out.println("Loading types");
        this.hostTypes = fileManager.loadHostTypes(file);
    }

    @Command
    public void saveHostTypes() {
        fileManager.saveHostTypes(hostTypes);
    }

    @Command
    public void saveGroupTypes() {
        fileManager.saveGroupTypes(groupTypes);
    }

    @Command
    public void saveHosts() {
        fileManager.saveHosts(hosts);
    }

    @Command
    public void saveGroups() {
        fileManager.saveGroups(groups);
    }

    @Command
    public void setBaseDir(String baseDir) {
        fileManager = new FileManager(serializer, new File(baseDir));
    }


    @Command
    public void bootHost(int index) {
        this.hosts.get(index).boot();
    }

    @Command
    public void activateHost(int index) {
        this.hosts.get(index).activate();
    }

    @Command
    public void deactivateHost(int index) {
        this.hosts.get(index).deactivate();
    }

    @Command
    public void shutdownHost(int index) {
        this.hosts.get(index).shutdown();
    }

    @Command
    public void bootGroup(int index) {
        this.groups.get(index).boot();
    }

    @Command
    public void shutdownGroup(int index) {
        this.groups.get(index).shutdown();
    }

    public Main() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        serializer = new Serializer(mapper);
        fileManager = new FileManager(serializer);
        loadAll();
    }

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell(">","hire brian", new Main()).commandLoop();
    }

}
