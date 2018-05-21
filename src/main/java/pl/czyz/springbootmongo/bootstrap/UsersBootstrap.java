package pl.czyz.springbootmongo.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.domain.UserMessage;
import pl.czyz.springbootmongo.domain.UserNode;
import pl.czyz.springbootmongo.repository.UsersNodeRepository;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Slf4j
@Component
public class UsersBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final UsersRepository usersRepository;
    private final UsersNodeRepository usersNodeRepository;

    @Autowired
    public UsersBootstrap(UsersRepository usersRepository, UsersNodeRepository usersNodeRepository) {
        this.usersRepository = usersRepository;
        this.usersNodeRepository = usersNodeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        cleanDatabases();
        loadPeople();
        log.debug("Loading Bootstrap data");
    }

    private void cleanDatabases() {
        usersRepository.deleteAll();
        usersNodeRepository.deleteAll();
    }

    private void loadPeople() {
        List<User> users = new ArrayList<>();
        IntStream.range(1, 20).
                forEach(i ->
                        users.add(
                                usersRepository.save(
                                        new User("name " + i, "surname " + i, "london", "login" + i,
                                                LocalDate.of(1990 + i, ThreadLocalRandom.current().nextInt(1, 13), ThreadLocalRandom.current().nextInt(1, 28))))
                        ));

        User login1 = usersRepository.findByLogin("login1");
        login1.publishMessage(new UserMessage("some Body"));
        usersRepository.save(login1);

        users.forEach(person -> usersNodeRepository.save(new UserNode(person.getLogin())));
    }
}
