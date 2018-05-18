package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.UserMessage;

import java.util.List;


public interface MessageService {

    void publishMessage(String currentUser, String content);

    List<UserMessage> myMessages(String currentUser);

}
