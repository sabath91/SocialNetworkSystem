package pl.czyz.springbootmongo.rotues;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Cucumber_UserInformationIT {


    @Autowired
    private UsersRepository usersRepository;

    @LocalServerPort
    private int port;

    private User user;

    private User[] response;

//    @Before
//    public void setUp() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = port;
//
//        user = new User("name", "surname", "city", "login", LocalDate.of(1988, 11, 9));
//        usersRepository.deleteAll();
//        usersRepository.save(user);
//
//    }


    @Given("HTTP GET request without headers")
    @When("I should ask service for list of users")
    public void i_should_ask_service_for_list_of_users() {
        response = given()
                .get("api/users").as(User[].class);
    }

    @Then("I will return list of users in json format")
    public void i_will_return_list_of_users_in_json_format() {

        assertEquals(user, response[0]);
    }

}
