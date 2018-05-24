package pl.czyz.springbootmongo.rotues;

import io.restassured.RestAssured;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
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
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.time.LocalDate;

import static io.restassured.RestAssured.when;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
//import static io.restassured.module.jsv.JsonSchemaValidator.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserInformationRouteIT extends CamelTestSupport {

    @Autowired
    private UsersRepository usersRepository;

    private User user;

    @LocalServerPort
    private int port;


    @Before
    public void setUp() throws Exception {
        this.usersRepository.deleteAll();
        this.user = new User("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
        this.usersRepository.save(user);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }


    @Test
    public void shouldReachEndpoint() {

        when().get("/api/users").then().statusCode(HttpStatus.SC_OK);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RootRoute();
    }

}