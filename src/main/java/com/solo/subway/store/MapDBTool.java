package com.solo.subway.store;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapDBTool {

    DB store;
    public MapDBTool() {
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

    public void close() {
        store.close();
    }

}
