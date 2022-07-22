package io.quarkiverse.issue.reproducer.deployment;

import javax.inject.Singleton;

import io.quarkiverse.issue.reproducer.runtime.ProblemObject;
import io.quarkiverse.issue.reproducer.runtime.ProblemObjectRecorder;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class IssueReproducerProcessor {

    private static final String FEATURE = "issue.reproducer";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    SyntheticBeanBuildItem problemObjectBuildStep(ProblemObjectRecorder problemObjectRecorder,
            BuildProducer<AdditionalBeanBuildItem> additionalBeanProducer) {
        ProblemObject problemObject = new ProblemObject();
        problemObject.setMyBoolean(true);

        additionalBeanProducer
                .produce(AdditionalBeanBuildItem.builder().addBeanClass(ProblemObject.class).setUnremovable().build());
        return SyntheticBeanBuildItem.configure(ProblemObjectRecorder.class)
                .scope(Singleton.class)
                .defaultBean()
                .supplier(problemObjectRecorder.problemObjectSupplier(problemObject))
                .done();
    }
}
