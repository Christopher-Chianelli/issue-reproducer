package ai.timefold.my.ext.runtime;

import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "my-ext")
@ConfigRoot(phase=ConfigPhase.RUN_TIME)
public interface MyExtRuntimeConfig {
    /**
     * Forces startup failure at runtime if true.
     */
    Optional<Boolean> failOnStartup();
}
