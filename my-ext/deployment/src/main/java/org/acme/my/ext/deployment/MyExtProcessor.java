package org.acme.my.ext.deployment;

import java.util.concurrent.atomic.AtomicInteger;

import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.gizmo.BytecodeCreator;
import io.quarkus.gizmo.FunctionCreator;
import io.quarkus.gizmo.ResultHandle;
import org.acme.my.ext.MyGenericInterface;
import org.jboss.jandex.DotName;
import org.jboss.jandex.ParameterizedType;
import org.jboss.jandex.Type;

class MyExtProcessor {

    private static final String FEATURE = "my-ext";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    SyntheticBeanBuildItem addMyGenericInterface() {
        DotName interfaceName = DotName.createSimple(MyGenericInterface.class.getName());
        DotName numberTypeName = DotName.createSimple(AtomicInteger.class.getName());
        DotName stringTypeName = DotName.createSimple(String.class.getName());
        return SyntheticBeanBuildItem.configure(interfaceName)
                .creator(methodCreator -> {
                    FunctionCreator functionCreator = methodCreator.createFunction(MyGenericInterface.class);
                    BytecodeCreator functionBytecode = functionCreator.getBytecode();
                    functionBytecode.returnValue(functionBytecode.loadNull());
                    methodCreator.returnValue(functionCreator.getInstance());
                })
                .types(ParameterizedType.create(interfaceName,
                                                new Type[]{
                                                        Type.create(numberTypeName, Type.Kind.CLASS),
                                                        Type.create(stringTypeName, Type.Kind.CLASS)
                                                }, null))
                .done();
    }
}
