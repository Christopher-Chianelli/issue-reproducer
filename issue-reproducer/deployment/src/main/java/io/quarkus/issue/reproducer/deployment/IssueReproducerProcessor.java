package io.quarkus.issue.reproducer.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.GeneratedClassBuildItem;
import io.quarkus.gizmo.ClassCreator;
import io.quarkus.gizmo.ClassOutput;

class IssueReproducerProcessor {

    private static final String FEATURE = "issue-reproducer";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
    
    @BuildStep
    void createGeneratedClasses(BuildProducer<GeneratedClassBuildItem> generatedClassConsumer,
                                BuildProducer<AdditionalBeanBuildItem> additionalBeanConsumer) {
        // GeneratedBeanGizmoAdaptor classOutput = new GeneratedBeanGizmoAdaptor(generatedBeanConsumer);
        String generatedClassName = "MyClazz";
        String[] clazzName = new String[1];
        byte[][] clazzBytecode = new byte[1][];
        generateClass((name, bytecode) -> {
            clazzName[0] = name;
            clazzBytecode[0] = bytecode;
        }, generatedClassName);
        generatedClassConsumer.produce(new GeneratedClassBuildItem(true, clazzName[0],
                                                                       clazzBytecode[0]));
        additionalBeanConsumer.produce(new AdditionalBeanBuildItem(clazzName[0]));
    }

    private void generateClass(ClassOutput classOutput, String generatedClassName) {
        ClassCreator classCreator = ClassCreator.builder()
                .classOutput(classOutput)
                .className(generatedClassName)
                .build();

        classCreator.close();
    }

}
