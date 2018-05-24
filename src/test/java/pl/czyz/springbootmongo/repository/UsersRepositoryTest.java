package pl.czyz.springbootmongo.repository;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.czyz.springbootmongo.domain.User;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private User user;

    @Before
    public void setUp() throws Exception {
        this.usersRepository.deleteAll();
        this.user = new User("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
        this.usersRepository.save(user);

    }

    @Test
    public void shouldFindByCity() {

        //when
        Stream<User> peopleResultStream = usersRepository.findAllByCity("City");
        User foundUser = peopleResultStream.findFirst().orElse(null);

        //then
        assertEquals(user, foundUser);

    }


}