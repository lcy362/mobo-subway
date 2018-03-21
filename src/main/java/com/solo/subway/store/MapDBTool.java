package com.solo.subway.store;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapDBTool {
    DB store;
    public MapDBTool(DB store) {
        this.store = store;
    }

    public <T> void save(String name, Map<String, T> data) {
        Map<String, T> savedData = store.hashMap(name, Serializer.STRING, Serializer.JAVA).createOrOpen();
        savedData.putAll(data);
    }

    public <T> Map<String, T> load(String name) {
        if (!store.exists(name)) {
            return Collections.emptyMap();
        }

        Map<String, T> savedData = store.hashMap(name, Serializer.STRING, Serializer.JAVA).open();
        Map<String, T> result = new HashMap<>();
        result.putAll(savedData);
        return result;

    }

}
