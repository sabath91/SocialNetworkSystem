package pl.czyz.springbootmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringbootMongoApplication implements CommandLineRunner{

    @Autowired
    PeopleRepository peopleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        peopleRepository.deleteAll();


        IntStream.range(1, 10).
                forEach(i -> peopleRepository.save(
                        new Person("name "+ i, "surname "+ i, "london",
                                LocalDate.of(1990+i, 2+i, 20+i)))
                );

        peopleRepository.findAllByCity("Kraków").forEach(System.out::println);


    }
}
