package pl.czyz.springbootmongo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Slf4j
@Component
public class PeopleBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final PeopleRepository peopleRepository;

    public PeopleBootstrap(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadPeople();
        log.debug("Loading Bootstrap data");
    }

    private void loadPeople() {
        peopleRepository.deleteAll();

        IntStream.range(1, 10).
                forEach(i -> peopleRepository.save(
                        new Person("name " + i, "surname " + i, "london",
                                LocalDate.of(1990 + i, 2 + i, 20 + i)))
                );

    }
}
