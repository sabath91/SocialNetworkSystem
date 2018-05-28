package pl.czyz.springbootmongo.rotues;

import io.restassured.RestAssured;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.domain.UserNode;
import pl.czyz.springbootmongo.repository.UsersNodeRepository;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInformationRouteIT {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersNodeRepository usersNodeRepository;

    @LocalServerPort
    private int port;


    @Before
    public void setUp() throws Exception {
        this.usersRepository.deleteAll();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }


    @Test
    public void shouldReachEndpoint() {
        when().get("/api/users").then().statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void shouldGetUserFromUsersEndpoint() {
        User user = new User("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
        this.usersRepository.save(user);

        //when
        User[] receivedUsers = when().get("api/users").as(User[].class);
        //then
        assertEquals(user, receivedUsers[0]);
    }

    @Test
    public void shouldSaveUserWithPostMethod() {
        User postUser = new User("UserName", "UserSurname", "SomeCity", "UserLogin", LocalDate.of(1989, 11, 11));

//      @formatter:off
        given()
            .contentType("application/json")
            .body(postUser)
        .when()
            .post("/api/users")
        .then()
            .statusCode(HttpStatus.SC_OK);
//       @formatter:on
    }

    @Test
    public void shouldSaveUserProperly() {
        User postUser = new User("UserName", "UserSurname", "SomeCity", "UserLogin", LocalDate.of(1989, 11, 11));
        //      @formatter:off
        given()
            .contentType("application/json")
            .body(postUser)
        .when()
            .post("/api/users")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("name", equalTo("UserName"))
            .body("surname", equalTo("UserSurname"))
            .body("city", equalTo("SomeCity"))
            .body("login", equalTo("UserLogin"))
            .body("dateOfBirth", equalTo("1989-11-11"));
//       @formatter:on
    }

    @Test
    public void testUsersApiAgainstSchema() {
        User user = new User("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
        this.usersRepository.save(user);
        when().get("/api/users").then().assertThat().body(matchesJsonSchemaInClasspath("users-schema.json"));
    }

    @Test
    public void shouldPersistUserWhenCreatingAsNodeInUserNodeRepository() {
        User user = new User("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
//        @formatter:off
        given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("/api/users")
        .then()
            .statusCode(HttpStatus.SC_OK);
//      @formatter:on


        UserNode userNode = usersNodeRepository.findByLogin(user.getLogin());
        assertNotNull(userNode);
        assertTrue(userNode.getFriends().isEmpty());
        assertTrue(userNode.getInvitations().isEmpty());
    }



}