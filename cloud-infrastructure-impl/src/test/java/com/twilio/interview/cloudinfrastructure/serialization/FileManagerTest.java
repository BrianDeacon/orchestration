package com.twilio.interview.cloudinfrastructure.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupState;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostState;
import com.twilio.interview.cloudinfrastructure.model.HostType;
import com.twilio.interview.cloudinfrastructure.model.impl.GroupImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.HostImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.HostTypeImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FileManagerTest {

    private FileManager fileManager;
    private Serializer serializer;

    @Before
    public void before() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        serializer = new Serializer(mapper);
        fileManager = new FileManager(serializer, new File("/tmp"));
    }
    @Test
    public void testSaveHosts() {
        HostImpl host = testHost();
        fileManager.saveHosts(Arrays.asList(host));

        List<Host> hosts = fileManager.loadHosts();
        assertEquals(1, hosts.size());
        assertEquals(host, hosts.iterator().next());
    }

    private HostImpl testHost() {
        HostImpl host = new HostImpl();
        host.setType("type");
        host.setSize(HostSize.MEDIUM);
        host.setDescription(UUID.randomUUID().toString());
        host.setState(HostState.CONFIGURING);
        return host;
    }

    @Test
    public void testSaveHostTypes() {
        HostTypeImpl type = new HostTypeImpl();
        type.setDescription(UUID.randomUUID().toString());
        type.setId(UUID.randomUUID().toString());
        type.setSize(HostSize.MEDIUM);
        type.setType(UUID.randomUUID().toString());
        fileManager.saveHostTypes(Arrays.asList(type));
        List<HostType> types = fileManager.loadHostTypes();
        assertEquals(1, types.size());
        assertEquals(type, types.iterator().next());
    }

    @Test
    public void testSaveGroups() {
        GroupImpl group = new GroupImpl();
        group.setState(GroupState.BOOTING);
        group.setType(UUID.randomUUID().toString());
        group.setSize(HostSize.MEDIUM);
        group.setId(UUID.randomUUID().toString());
        group.setDescription(UUID.randomUUID().toString());
        group.setHosts(Arrays.asList(testHost()));

        fileManager.saveGroups(Arrays.asList(group));

        List<Group> groups = fileManager.loadGroups();
        assertEquals(1, groups.size());
        assertEquals(group, groups.iterator().next());

    }
}
