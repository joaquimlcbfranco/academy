package com.ctw.workstation.externals;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class HelloExtApiAcademy {

    @RestClient
    ExternalApi externalApi;

    public String sayHello(String name) {
        if(name != null) {
            return externalApi.hello(new ExternalRequest(name)).message();
        }

        return externalApi.hello().message();
    }
}
