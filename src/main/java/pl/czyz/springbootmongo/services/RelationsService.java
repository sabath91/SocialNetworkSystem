package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.Person;

import java.util.List;

public interface RelationsService {

    void savePersonAsNode(Person person);

    void sendInvitation(String currentUserLogin, String addresseeLogin);

    List<Person> myInvitations(String currentUser);

    void acceptInvitation(String senderLogin, String currentUserLogin);

    void declineInvitation(String senderLogin, String currentUserLogin);

    void deleteFriendship(String currentUserLogin, String friendToDeleteLogin);

    List<Person> myFriends(String currentUser);

    List<Person> myNetwork(String currentUser);

    Integer distanceFactor(String currentUser, String destinationUser);
}
