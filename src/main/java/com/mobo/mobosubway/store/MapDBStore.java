package com.mobo.mobosubway.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapDBStore {

    private DB store;

    @PostConstruct
    public void init() {
        store = DBMaker.fileDB("file.db").make();
    }


    public <T> void save(String name, Map<String, T> data) {
        Map<String, T> savedData = store.hashMap(name, Serializer.STRING, Serializer.JAVA).createOrOpen();
        savedData.putAll(data);
    }

    public <T> Map<String, T> load(String name) {
        if (!store.exists(name)) {
            return new HashMap<>();
        }

        Map<String, T> savedData = store.hashMap(name, Serializer.STRING, Serializer.JAVA).open();
        Map<String, T> result = new HashMap<>();
        result.putAll(savedData);
        return result;

    }

    @PreDestroy
    public void close() {
        if (store != null && !store.isClosed()) {
            store.close();
        }
    }

}
