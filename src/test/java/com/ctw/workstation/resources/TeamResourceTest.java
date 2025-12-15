package com.ctw.workstation.resources;

import com.ctw.workstation.config.GameboyClassResourceTest;
import com.ctw.workstation.data.entity.Team;
import com.ctw.workstation.data.repository.TeamRepository;
import com.ctw.workstation.dto.TeamDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;


@QuarkusTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@QuarkusTestResource(GameboyClassResourceTest.class)
@TestHTTPEndpoint(TeamResource.class)
class TeamResourceTest {

    @Inject
    TeamResource teamResource;

    @Inject
    Logger logger;

//    @InjectMock
//    TeamRepository teamRepositoryMock;


    public TeamDTO buildDTO() {
        return new TeamDTO("Java", "Oracle", "USA");
    }

    public static TeamDTO buildDTOWithParams(String name, String productName, String defaultLocation) {
        return new TeamDTO(name, productName, defaultLocation);
    }

    public static Stream<Arguments> when_post_with_empty_fields_return_invalid_attributes_with_message() {
        return Stream.of(
                Arguments.of(buildDTOWithParams("", "Oracle", "USA")),
                Arguments.of(buildDTOWithParams("Java", "", "USA")),
                Arguments.of(buildDTOWithParams("Java", "Oracle", "")),
                Arguments.of(buildDTOWithParams("", "", "USA")),
                Arguments.arguments(buildDTOWithParams("", "", ""))
        );
    }

    public static Stream<Arguments>  when_post_with_null_fields_return_error() {
        return Stream.of(
                Arguments.of(buildDTOWithParams(null, "Oracle", "USA")),
                Arguments.of(buildDTOWithParams("Java", null, "USA")),
                Arguments.of(buildDTOWithParams("Java", "Oracle", null))
        );
    }

    @Test
    @Order(1)
    void when_post_team_return_response_with_team() {
        TeamDTO dto = buildDTO();

        given().contentType(ContentType.JSON)
                .body(dto)
            .when()
                .post()
            .then()
                .assertThat()
                .statusCode(201)
                .body(
                        "productName", equalTo("Java"),
                        "name", equalTo("Oracle"),
                        "defaultLocation", equalTo("USA"),
                        "id", notNullValue(Long.class)
                );
    }

    @ParameterizedTest
    @MethodSource
    void when_post_with_null_fields_return_error(TeamDTO values) {
        given().contentType(ContentType.JSON).body(values)
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(500);
    }

    @ParameterizedTest
    @MethodSource
    void when_post_with_empty_fields_return_invalid_attributes_with_message(TeamDTO values) {
        given().contentType(ContentType.JSON).body(values)
            .when()
                .post()
            .then()
                .assertThat()
                .statusCode(400)
                .body("error", equalTo("name, productName and defaultLocation must not be empty"));
    }

    @Test
    @Order(2)
    void post_team_and_store_in_variable() {
        TeamDTO response = given().contentType(ContentType.JSON).body(buildDTO())
                        .when()
                            .post()
                        .then()
                            .assertThat()
                            .statusCode(201)
                            .extract()
                            .as(TeamDTO.class);

        assertThat(response)
                .as("Response is not null")
                .isNotNull()
                .as("Response ID is not null")
                .hasFieldOrProperty("id")
                .as("All DTO fields are present and matching the original DTO")
                .hasFieldOrPropertyWithValue("productName", "Java")
                .hasFieldOrPropertyWithValue("name", "Oracle")
                .hasFieldOrPropertyWithValue("defaultLocation", "USA");


    }

    @Test
    @Order(3)
    void get_teams() {
        TeamDTO compared = buildDTO();
        List<TeamDTO> response = get()
                                    .then()
                                        .extract()
                                        .body()
                                        .jsonPath()
                                        .getList(".", TeamDTO.class);

        assertThat(response)
                .as("List is of size 2 after using post method twice")
                .hasSize(2);

//        for (TeamDTO dto : response) {
//            assertThat(dto)
//                    .as("Response is not null")
//                    .isNotNull()
//                    .as("Response ID is not null")
//                    .hasFieldOrProperty("id")
//                    .as("All DTO fields are present and matching the original DTO")
//                    .usingRecursiveComparison()
//                    .ignoringFields("id", "createdAt", "modifiedAt")
//                    .isEqualTo(compared);
//        }

        assertThat(response)
                .allSatisfy(
                        dto -> assertThat(dto)
                                            .usingRecursiveComparison()
                                            .ignoringFields("id", "createdAt", "modifiedAt")
                                            .isEqualTo(compared)
                );
    }

    @Test
    @Order(4)
    void get_single_team() {
        TeamDTO dto = given().contentType(ContentType.JSON)
                        .when()
                            .get("/1")
                        .then()
                            .assertThat()
                            .statusCode(200)
                            .body("id", equalTo(1))
                            .extract()
                            .as(TeamDTO.class);

        Long id = dto.getId();

        assertThat(dto)
                .as("Response is not null")
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("id",  "createdAt", "modifiedAt")
                .isEqualTo(buildDTO());
    }

    @ParameterizedTest
    @ValueSource(longs = {20, 300, -1, 12})
    void get_single_team_that_does_not_exist_should_return_entity_not_found(Long value) {
        given().contentType(ContentType.JSON).pathParam("value", value)
            .when()
                .get("/{value}")
            .then()
                .assertThat()
                .statusCode(404)
                .body("error", equalTo("Team with id %d not found".formatted(value)));
    }

    @Test
    @Order(5)
    void delete_team() {
        given().contentType(ContentType.JSON)
            .when()
                .delete("/1")
            .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    @Order(6)
    void get_team_is_null_after_delete() {
        given().contentType(ContentType.JSON)
            .when()
                .get("/1")
            .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    @Order(7)
    void create_team_after_delete() {
        TeamDTO payload = buildDTO();
        TeamDTO response = given().contentType(ContentType.JSON).body(payload)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(TeamDTO.class);

        assertThat(response)
                .as("Response is not null")
                .isNotNull()
                .as("Response ID is not null")
                .hasFieldOrProperty("id")
                .as("All DTO fields are present and matching the original DTO")
                .usingRecursiveComparison()
                .ignoringFields("id",  "createdAt", "modifiedAt")
                .isEqualTo(payload);
    }

    @Test
    @Order(8)
    void delete_all_teams() {
        given().contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Order(9)
    void get_teams_after_delete_all() {
        List<TeamDTO> data = given().contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList(".", TeamDTO.class);

        assertThat(data)
                .as("Response is not null")
                .isNotNull()
                .as("List is of size 0 after deleting all entries")
                .hasSize(0);
    }

    @Test
    @DisplayName("When creating a team an internal error occurs, a 500 is returned")
    void when_creating_a_team_an_internal_error_occurs() {
        TeamRepository teamRepositoryMock = Mockito.mock(TeamRepository.class);
        QuarkusMock.installMockForType(teamRepositoryMock, TeamRepository.class);

        TeamDTO payload = buildDTO();

        Mockito.doThrow(IllegalArgumentException.class)
                        .when(teamRepositoryMock)
                        .persist(Mockito.any(Team.class));

        given()
                .contentType(ContentType.JSON)
                .body(payload)
            .when()
                .post()
            .then()
                .log()
                .all()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
}