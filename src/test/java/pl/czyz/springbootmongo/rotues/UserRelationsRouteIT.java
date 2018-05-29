package pl.czyz.springbootmongo.rotues;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.czyz.springbootmongo.SpringbootMongoApplication;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.repository.UsersNodeRepository;
import pl.czyz.springbootmongo.repository.UsersRepository;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootMongoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRelationsRouteIT {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersNodeRepository usersNodeRepository;

    @LocalServerPort
    private int port;

    private User firstUser;
    private User secondUser;


    @Before
    public void setUp() throws Exception {
        this.usersRepository.deleteAll();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        firstUser = new User("firstUser", "firstUser", "firstUser", "firstUser", LocalDate.of(1999, 11, 21));
        secondUser = new User("secondUser", "secondUser", "secondUser", "secondUser", LocalDate.of(1989, 11, 21));

        prepareUsersInApplicationContext(Arrays.asList(firstUser, secondUser));
    }

    private void prepareUsersInApplicationContext(List<User> users) {

        users.forEach(
                user -> {
                    //@formatter:off
                    given()
                        .contentType("application/json")
                        .body(user)
                    .when()
                        .post("/api/users");
                //@formatter:on
                }
        );

    }

    @Test
    public void shouldSendInvitationFromFirstUserToSecondUser() {
        //@formatter:off
        given()
            .post("/api/user/firstUser/sendInvitation/secondUser")
        .then()
            .statusCode(HttpStatus.SC_OK);


           assertEquals(1,usersNodeRepository.findMyInvitations("secondUser").size());

    }


}