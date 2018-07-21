package com.twilio.interview.cloudinfrastructure.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.twilio.interview.cloudinfrastructure.model.Group;
import com.twilio.interview.cloudinfrastructure.model.GroupType;
import com.twilio.interview.cloudinfrastructure.model.Host;
import com.twilio.interview.cloudinfrastructure.model.HostType;
import com.twilio.interview.cloudinfrastructure.model.impl.GroupImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.GroupTypeImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.HostImpl;
import com.twilio.interview.cloudinfrastructure.model.impl.HostTypeImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Serializer {
    private final ObjectMapper objectMapper;

    public Serializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String write(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Object object, Writer writer) {
        try {
            objectMapper.writeValue(writer, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(Object object, File file) {
        try {
            write(object, new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T safeRead(Reader reader, Class<T> clazz) {
        try {
            return objectMapper.readValue(reader, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T safeRead(File f, Class<T> clazz) {
        try {
            return safeRead(new FileReader(f), clazz);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Host> readHosts(File file) {
        if (!file.exists()) return Collections.emptyList();
        return Arrays.asList(safeRead(file, HostImpl[].class));
    }

    public List<HostType> readHostTypes(File file) {
        if (!file.exists()) return Collections.emptyList();
        return Arrays.asList(safeRead(file, HostTypeImpl[].class));
    }

    public List<Group> readGroups(File file) {
        if (!file.exists()) return Collections.emptyList();
        return Arrays.asList(safeRead(file, GroupImpl[].class));
    }


    public List<GroupType> readGroupTypes(File file) {
        if (!file.exists()) return Collections.emptyList();
        return Arrays.asList(safeRead(file, GroupTypeImpl[].class));
    }

}
