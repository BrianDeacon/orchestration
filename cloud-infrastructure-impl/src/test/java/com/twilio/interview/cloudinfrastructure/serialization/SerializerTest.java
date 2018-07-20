package com.twilio.interview.cloudinfrastructure.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupState;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostSize;
import com.twilio.interview.cloudinfrastructure.model.HostState;
import com.twilio.interview.cloudinfrastructure.model.impl.GroupImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.HostImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class SerializerTest {

    private ObjectMapper mapper;
    private Serializer serializer;

    @Before
    public void before() {
        this.mapper = new ObjectMapper();
        this.serializer = new Serializer(mapper);
    }

    @Test
    public void testWriteGroup() {
        GroupImpl group = new GroupImpl();
        group.setDescription(UUID.randomUUID().toString());
        group.setId(UUID.randomUUID().toString());
        group.setSize(HostSize.LARGE);
        group.setType(UUID.randomUUID().toString());
        group.setState(GroupState.BOOTING);
        List<Host> hosts = new ArrayList<>();
        group.setHosts(hosts);
        HostImpl host = new HostImpl();
        hosts.add(host);
        host.setActive(true);
        host.setDescription(UUID.randomUUID().toString());
        host.setId(UUID.randomUUID().toString());
        host.setSize(HostSize.MEDIUM);
        host.setState(HostState.CONFIGURING);
        host.setType(UUID.randomUUID().toString());

        String json = serializer.write(group);

        assertTrue(json.contains(group.getDescription()));
        assertTrue(json.contains(group.getId()));
        assertTrue(json.contains(group.getSize().name()));
        assertTrue(json.contains(group.getType()));
        assertTrue(json.contains(group.getState().name()));
        assertTrue(json.contains(host.getDescription()));
        assertTrue(json.contains(host.getId()));
        assertTrue(json.contains(host.getType()));
        assertTrue(json.contains(host.getSize().name()));
        assertTrue(json.contains(host.getState().name()));
    }
}
