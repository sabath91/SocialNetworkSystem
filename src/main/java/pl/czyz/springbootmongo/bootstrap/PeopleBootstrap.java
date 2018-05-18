package pl.czyz.springbootmongo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.domain.PersonNode;
import pl.czyz.springbootmongo.domain.UserMessage;
import pl.czyz.springbootmongo.repository.PeopleNodeRepository;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Component
public class PeopleBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final PeopleRepository peopleRepository;
    private final PeopleNodeRepository peopleNodeRepository;

    @Autowired
    public PeopleBootstrap(PeopleRepository peopleRepository, PeopleNodeRepository peopleNodeRepository) {
        this.peopleRepository = peopleRepository;
        this.peopleNodeRepository = peopleNodeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        cleanDatabases();
        loadPeople();
        log.debug("Loading Bootstrap data");
    }

    private void cleanDatabases() {
        peopleRepository.deleteAll();
        peopleNodeRepository.deleteAll();
    }

    private void loadPeople() {
        List<Person> users = new ArrayList<>();
        IntStream.range(1, 20).
                forEach(i ->
                        users.add(
                                peopleRepository.save(
                                        new Person("name " + i, "surname " + i, "london", "login" + i,
                                                LocalDate.of(1990 + i, ThreadLocalRandom.current().nextInt(1, 13), ThreadLocalRandom.current().nextInt(1, 28))))
                        ));

        Person login1 = peopleRepository.findByLogin("login1");
        login1.publishMessage(new UserMessage("some Body"));
        peopleRepository.save(login1);

        users.forEach(person -> peopleNodeRepository.save(new PersonNode(person.getLogin())));
    }
}
