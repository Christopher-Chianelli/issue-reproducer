package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Job {
    @PlanningId
    String id;
    String jobId;
    String roleId;
    User user;
    int cost;

    @PlanningVariable(valueRangeProviderRefs = { "selectedList" })
    Integer selected;

    public Job() {
    }

    public Job(String id, String jobId, String roleId, User user, int cost) {
        this.id = id;
        this.jobId = jobId;
        this.roleId = roleId;
        this.user = user;
        this.cost = cost;
    }

    public Job(String id, String jobId, String roleId, User user, int cost, int selected) {
        this.id = id;
        this.jobId = jobId;
        this.roleId = roleId;
        this.user = user;
        this.cost = cost;
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
