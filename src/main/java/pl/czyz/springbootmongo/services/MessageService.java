package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.helpers.MessageRepresentation;

import java.util.List;


public interface MessageService {

    void publishMessage(String currentUser, String content);

    List<MessageRepresentation> myMessages(String currentUser);

    List<MessageRepresentation> friendsMessages(List<User> users);

    List<MessageRepresentation> networkMessages(List<User> users);



}
