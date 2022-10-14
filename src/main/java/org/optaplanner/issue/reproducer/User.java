package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.lookup.PlanningId;

public class User {

    @PlanningId
    String id;
    int capacity;
    int existsCost;

    public User() {

    }

    public User(String id, int capacity, int existsCost) {
        this.id = id;
        this.capacity = capacity;
        this.existsCost = existsCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getExistsCost() {
        return existsCost;
    }

    public void setExistsCost(int existsCost) {
        this.existsCost = existsCost;
    }
}
