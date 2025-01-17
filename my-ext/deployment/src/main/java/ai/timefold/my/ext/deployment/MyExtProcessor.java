package ai.timefold.my.ext.deployment;

import static io.quarkus.deployment.annotations.ExecutionTime.RUNTIME_INIT;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import ai.timefold.my.ext.runtime.MyBean;
import ai.timefold.my.ext.runtime.MyExtRecorder;

import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.recording.RecorderContext;

class MyExtProcessor {

    private static final String FEATURE = "my-ext";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(RUNTIME_INIT)
    void buildSyntheticBean(MyExtRecorder recorder,
            BuildProducer<SyntheticBeanBuildItem> syntheticBeans) {
        syntheticBeans.produce(SyntheticBeanBuildItem.configure(MyBean.class)
                .scope(Singleton.class)
                .supplier(recorder.myBeanSupplier())
                .setRuntimeInit()
                .defaultBean()
                .done());
    }
}
