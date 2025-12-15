package com.ctw.workstation.config;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class GameboyClassResourceTest implements QuarkusTestResourceLifecycleManager {
    @Override
    public void stop() {

    }

    @Override
    public void setContext(Context context) {
        QuarkusTestResourceLifecycleManager.super.setContext(context);
    }

    @Override
    public void init(Map<String, String> initArgs) {
        Log.info("GameClassResourceTest starting init");
        QuarkusTestResourceLifecycleManager.super.init(initArgs);
    }

    @Override
    public void inject(Object testInstance) {
        QuarkusTestResourceLifecycleManager.super.inject(testInstance);
    }

    @Override
    public void inject(TestInjector testInjector) {
        QuarkusTestResourceLifecycleManager.super.inject(testInjector);
    }

    @Override
    public int order() {
        return QuarkusTestResourceLifecycleManager.super.order();
    }

    @Override
    public Map<String, String> start() {
        Log.info("GameClassResourceTest starting");
        return Map.of("quarkus.log.console.json", "false");
    }
}
