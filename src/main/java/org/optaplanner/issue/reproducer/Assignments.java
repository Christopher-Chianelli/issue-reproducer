package org.optaplanner.issue.reproducer;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Assignments {
    @ValueRangeProvider(id = "selectedList")
    List<Integer> selectedList;

    @PlanningEntityCollectionProperty
    List<Job> jobList;

    @PlanningScore
    HardSoftScore score;

    public Assignments() {
    }

    public Assignments(List<Integer> selectedList, List<Job> jobList) {
        this.selectedList = selectedList;
        this.jobList = jobList;
    }

    public Assignments(List<Integer> selectedList, List<Job> jobList, HardSoftScore score) {
        this.selectedList = selectedList;
        this.jobList = jobList;
        this.score = score;
    }

    public List<Integer> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<Integer> selectedList) {
        this.selectedList = selectedList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
