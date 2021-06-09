package org.optaplanner.issue.reproducer;

public class ExtendedEntityWithId extends EntityWithId {
    private String value;

    public ExtendedEntityWithId() {
    }

    public ExtendedEntityWithId(long id) {
        super(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
