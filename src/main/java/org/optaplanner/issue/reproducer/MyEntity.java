package org.optaplanner.issue.reproducer;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class MyEntity extends PanacheEntity {
    String name;
    boolean isAlive;

    public MyEntity() {}

    public MyEntity(String name, boolean isAlive) {
        this.name = name;
        this.isAlive = isAlive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public static List<MyEntity> getEntitiesWithNamesAndLifeStatus(long id, Set<String> names, boolean isAlive) {
        return list("id = ?1 and name in ?2 and isAlive = ?3", id, names, isAlive);
    }
}
