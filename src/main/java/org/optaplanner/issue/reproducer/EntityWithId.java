package org.optaplanner.issue.reproducer;

abstract class EntityWithId {
    private Long id;

    public EntityWithId() {
    }

    public EntityWithId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
