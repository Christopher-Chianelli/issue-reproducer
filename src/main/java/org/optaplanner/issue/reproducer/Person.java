package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Person {
    String name;

    @PlanningVariable(valueRangeProviderRefs = "valueRange")
    String value;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
