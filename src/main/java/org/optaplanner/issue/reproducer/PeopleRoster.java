package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.util.List;

@PlanningSolution
public class PeopleRoster {
    @PlanningScore
    SimpleScore score;

    @PlanningEntityCollectionProperty
    List<Person> personList;

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "valueRange")
    List<String> valueList;

    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    @Override public String toString() {
        return "PeopleRoster{" +
                "score=" + score +
                ", personList=" + personList +
                ", valueList=" + valueList +
                '}';
    }
}
