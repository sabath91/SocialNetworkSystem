package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.domain.UserMessage;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.util.List;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public MessageServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;

    }

    @Override
    public void publishMessage(String currentUserLogin, String content) {
        Person currentUser = peopleRepository.findByLogin(currentUserLogin);
        currentUser.publishMessage(new UserMessage(content));
        peopleRepository.save(currentUser);
    }

    @Override
    public List<UserMessage> myMessages(String currentUser) {
        return peopleRepository.findByLogin(currentUser).getPublishedMessages();

    }
}
