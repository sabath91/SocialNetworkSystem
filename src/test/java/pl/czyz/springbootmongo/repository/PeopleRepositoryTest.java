package pl.czyz.springbootmongo.repository;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.czyz.springbootmongo.domain.Person;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    private Person person;

    @Before
    public void setUp() throws Exception {
        this.peopleRepository.deleteAll();
        this.person = new Person("name", "surname", "City", RandomStringUtils.randomAlphanumeric(8), LocalDate.of(1980, 11, 24));
        this.peopleRepository.save(person);

    }

    @Test
    public void shouldFindByCity() {

        //when
        Stream<Person> peopleResultStream = peopleRepository.findAllByCity("City");
        Person foundPerson = peopleResultStream.findFirst().orElse(null);

        //then
        assertEquals(person, foundPerson);

    }
}