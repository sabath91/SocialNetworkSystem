package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.domain.UserMessage;
import pl.czyz.springbootmongo.helpers.MessageRepresentation;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    private final UsersRepository usersRepository;

    @Autowired
    public MessageServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public void publishMessage(String currentUserLogin, String content) {
        User currentUser = usersRepository.findByLogin(currentUserLogin);
        currentUser.publishMessage(new UserMessage(content));
        usersRepository.save(currentUser);
    }

    @Override
    public List<MessageRepresentation> myMessages(String currentUserLogin) {

        List<MessageRepresentation> messages = new ArrayList<>();
        User currentUser = usersRepository.findByLogin(currentUserLogin);
        List<UserMessage> currentUserMessages = currentUser.getPublishedMessages();

        currentUserMessages.forEach(message -> messages.add(new MessageRepresentation(message, currentUser)));
        return messages;
    }

    @Override
    public List<MessageRepresentation> friendsMessages(List<User> users) {
        List<MessageRepresentation> messages = new ArrayList<>();

        users.forEach(user -> {
            user.getPublishedMessages().forEach(message -> {
                messages.add(new MessageRepresentation(message, user));
            });
        });
        sort(messages);
        return messages;
    }

    @Override
    public List<MessageRepresentation> networkMessages(List<User> users) {
        List<MessageRepresentation> messages = new ArrayList<>();

        users.forEach(user -> {
            user.getPublishedMessages().forEach(message -> {
                messages.add(new MessageRepresentation(message, user));
            });
        });
        sort(messages);
        return messages;

    }


}
