package com.ctw.workstation.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wireMockServer;

    @Override
    public void init(Map<String, String> initArgs) {
        Log.info("WiremockResource init");
        wireMockServer = new WireMockServer(WireMockConfiguration.options().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
    }

    @Override
    public Map<String, String> start() {
        Log.info("Starting WireMockServer");
        return Map.of("external-api.url", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        Log.info("Stopping WireMockServer");
        wireMockServer.stop();
    }
}
