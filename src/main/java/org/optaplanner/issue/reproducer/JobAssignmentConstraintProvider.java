package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.bi.BiConstraintStream;
import org.optaplanner.core.api.score.stream.uni.UniConstraintStream;

public class JobAssignmentConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                jobConflict(constraintFactory),
                jobConflict1(constraintFactory),
                minimizeVariance(constraintFactory)
        };
    }

    Constraint jobConflict(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Job.class)
                .groupBy(job -> job.jobId, ConstraintCollectors.sum(job -> job.selected))
                .filter((jobId, selectedCount) -> selectedCount > 1)
                .penalize("only_one_user", HardSoftScore.ONE_HARD, (jobId, selectedCount) -> selectedCount - 1);
    }

    Constraint jobConflict1(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Job.class)
                .groupBy(job -> job.jobId, ConstraintCollectors.sum(job -> job.selected))
                .filter((jobId, selectedCount) -> selectedCount == 0)
                .penalize("must_one_user", HardSoftScore.ONE_HARD);
    }

    static double calculation(User user, int cost) {
        return (user.existsCost + cost) * 10000 / ((double) user.capacity);
    }

    BiConstraintStream<User, Integer> getTotalJobCostPerUser(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(Job.class)
                .groupBy(job -> job.user,
                        ConstraintCollectors.conditionally(job -> job.selected == 1,
                                ConstraintCollectors.sum(job -> job.cost)));
    }

    UniConstraintStream<Integer> getUserWorkload(ConstraintFactory constraintFactory) {
        return getTotalJobCostPerUser(constraintFactory)
                .groupBy((user, cost) -> (Integer) (int) Math.floor(calculation(user, cost)));
    }

    UniConstraintStream<Double> getAverageWorkload(ConstraintFactory constraintFactory) {
        return getUserWorkload(constraintFactory)
                .groupBy(ConstraintCollectors.average(workload -> workload));
    }

    static int calculateVariance(int workload, double average) {
        int difference = workload - (int) Math.floor(average);
        return difference * difference;
    }

    Constraint minimizeVariance(ConstraintFactory constraintFactory) {
        return getUserWorkload(constraintFactory)
                .join(getAverageWorkload(constraintFactory))
                .groupBy(ConstraintCollectors.sum(JobAssignmentConstraintProvider::calculateVariance))
                .penalize("Minimize variance", HardSoftScore.ONE_SOFT, variance -> variance);
    }
}
