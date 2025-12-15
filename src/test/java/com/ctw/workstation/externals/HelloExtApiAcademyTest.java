package com.ctw.workstation.externals;

import com.ctw.workstation.config.WiremockResource;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.factory.ObjectMapperFactory;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(WiremockResource.class)
class HelloExtApiAcademyTest {

    @Inject
    HelloExtApiAcademy helloExtApiAcademy;



    @Test
    @DisplayName("When providing a null name, should return greeting message from external API")
    void when_providing_a_null_name_should_return_greeting_message_from_external_api() {
        // given
        String jsonString = """
                    {"message": "Hello from external API"}
                """;

        stubFor(get(urlEqualTo("/external/hello"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonString)
                        .withStatus(200)
        ));

//        stubFor(get(urlEqualTo("/external/hello"))
//                .willReturn(okJson(jsonString))
//        );
        // when
        String actualResponse = helloExtApiAcademy.sayHello(null);
        // then
        Assertions.assertThat(actualResponse)
                .as("Returns hello from external API")
                .isEqualTo("Hello from external API");

    }

    @Test
    @DisplayName("When providing a non-null name, should post the name to database")
    void when_providing_a_nonnull_name_should_post_the_name_to_database() throws JsonProcessingException {
        String name = "Requim";

        ExternalRequest request = new ExternalRequest(name);
        String json = new ObjectMapper().writeValueAsString(request);

        ExternalResponse response = new ExternalResponse("Hello from external API to %s".formatted(name));
        String jsonResponse = new ObjectMapper().writeValueAsString(response);

        stubFor(post(urlEqualTo("/external/hello"))
                .withRequestBody(equalToJson(json))
                .willReturn(created()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResponse)
                        .withStatus(201)
                )
        );

        String actualResponse = helloExtApiAcademy.sayHello(name);

        Assertions.assertThat(actualResponse)
                .as("Says hello to created user from external API")
                .isEqualTo("Hello from external API to %s".formatted(name));


    }

}