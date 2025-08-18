package com.wsw02.selfdefine.exercise01;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author loriyuhv
 * @date 2025/8/18
 * @description DAO
 */
public class DAO<T> {
    Map<String, T> map;
    {
        map = new HashMap<>();
    }

    public void save(String id, T entity) {
        if(!this.map.containsKey(id)) {
            this.map.put(id, entity);
        }
    }

    public T get(String id) {
        return this.map.get(id);
    }

    public void update(String id, T entity) {
        if (this.map.containsKey(id)) {
            this.map.put(id, entity);
        }
    }

    public List<T> list() {
        Collection<T> values = map.values();
        return new ArrayList<>(values);
    }

    public void delete(String id) {
        this.map.remove(id);
    }
}
