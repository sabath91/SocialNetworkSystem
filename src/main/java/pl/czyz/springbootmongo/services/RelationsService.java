package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.User;

import java.util.List;

public interface RelationsService {

    void savePersonAsNode(User user);

    void sendInvitation(String currentUserLogin, String addresseeLogin);

    List<User> myInvitations(String currentUser);

    void acceptInvitation(String senderLogin, String currentUserLogin);

    void declineInvitation(String senderLogin, String currentUserLogin);

    void deleteFriendship(String currentUserLogin, String friendToDeleteLogin);

    List<User> myFriends(String currentUser);

    List<User> myNetwork(String currentUser);

    Integer distanceFactor(String currentUser, String destinationUser);

}
