package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.domain.UserNode;
import pl.czyz.springbootmongo.repository.UsersNodeRepository;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service(value = "relationsService")
public class RelationsServiceImpl implements RelationsService {

    private final UsersNodeRepository usersNodeRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public RelationsServiceImpl(UsersNodeRepository usersNodeRepository, UsersRepository usersRepository) {
        this.usersNodeRepository = usersNodeRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void savePersonAsNode(User user) {
        UserNode userNode = new UserNode(user.getLogin());
        usersNodeRepository.save(userNode);
    }

    @Override
    public void sendInvitation(String currentUserLogin, String addresseeLogin) {
        usersNodeRepository.sendInvitation(currentUserLogin, addresseeLogin);
    }

    @Override
    public List<User> myInvitations(String currentUserLogin) {
        List<User> friends = new ArrayList<>();
        Set<UserNode> invitations = usersNodeRepository.findMyInvitations(currentUserLogin);
        invitations.forEach(invitation -> friends.add(usersRepository.findByLogin(invitation.getLogin())));
        return friends;
    }

    @Override
    public void acceptInvitation(String senderLogin, String currentUserLogin) {
        usersNodeRepository.deleteInvitationAndMarkAsFriends(currentUserLogin, senderLogin);
    }

    @Override
    public void declineInvitation(String senderLogin, String currentUserLogin) {
        usersNodeRepository.declineInvitation(currentUserLogin, senderLogin);
    }

    @Override
    public void deleteFriendship(String currentUserLogin, String friendToDeleteLogin) {
        usersNodeRepository.deleteFriendship(currentUserLogin, friendToDeleteLogin);
    }

    @Override
    public List<User> myFriends(String currentUser) {
        List<User> friendsList = new ArrayList<>();
        List<UserNode> allFriends = usersNodeRepository.findAllFriends(currentUser);
        allFriends.forEach(userNode -> friendsList.add(usersRepository.findByLogin(userNode.getLogin())));
        return friendsList;
    }

    @Override
    public List<User> myNetwork(String currentUser) {
        List<User> network = new ArrayList<>();
        List<UserNode> allNetwork = usersNodeRepository.findNetwork(currentUser);
        allNetwork.forEach(userNode -> network.add(usersRepository.findByLogin(userNode.getLogin())));
        return network;
    }

    @Override
    public Integer distanceFactor(String currentUser, String destinationUser) {
        return Optional.ofNullable(usersNodeRepository.distanceFactor(currentUser, destinationUser)).orElse(0);
    }

    public String hello(String result) {
        return "hello " + result;
    }


}
