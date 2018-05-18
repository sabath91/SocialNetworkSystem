package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.domain.PersonNode;
import pl.czyz.springbootmongo.repository.PeopleNodeRepository;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "relationsService")
public class RelationsServiceImpl implements RelationsService {

    private final PeopleNodeRepository peopleNodeRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public RelationsServiceImpl(PeopleNodeRepository peopleNodeRepository, PeopleRepository peopleRepository) {
        this.peopleNodeRepository = peopleNodeRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public void savePersonAsNode(Person person) {
        PersonNode personNode = new PersonNode(person.getLogin());
        peopleNodeRepository.save(personNode);
    }

    @Override
    public void sendInvitation(String currentUserLogin, String addresseeLogin) {
        peopleNodeRepository.sendInvitation(currentUserLogin, addresseeLogin);
    }

    @Override
    public List<Person> myInvitations(String currentUserLogin) {
        List<Person> friends = new ArrayList<>();
        Set<PersonNode> invitations = peopleNodeRepository.findMyInvitations(currentUserLogin);
        invitations.forEach(invitation -> friends.add(peopleRepository.findByLogin(invitation.getLogin())));
        return friends;
    }

    @Override
    public void acceptInvitation(String senderLogin, String currentUserLogin) {
        peopleNodeRepository.deleteInvitationAndMarkAsFriends(currentUserLogin, senderLogin);
    }

    @Override
    public void declineInvitation(String senderLogin, String currentUserLogin) {
        peopleNodeRepository.declineInvitation(currentUserLogin, senderLogin);
    }

    @Override
    public void deleteFriendship(String currentUserLogin, String friendToDeleteLogin) {
        peopleNodeRepository.deleteFriendship(currentUserLogin, friendToDeleteLogin);
    }

    @Override
    public List<Person> myFriends(String currentUser) {
        List<Person> friendsList = new ArrayList<>();
        List<PersonNode> allFriends = peopleNodeRepository.findAllFriends(currentUser);
        allFriends.forEach(personNode -> friendsList.add(peopleRepository.findByLogin(personNode.getLogin())));
        return friendsList;
    }

    @Override
    public List<Person> myNetwork(String currentUser) {
        List<Person> network = new ArrayList<>();
        List<PersonNode> allNetwork = peopleNodeRepository.findNetwork(currentUser);
        allNetwork.forEach(personNode -> network.add(peopleRepository.findByLogin(personNode.getLogin())));
        return network;
    }

    @Override
    public Integer distanceFactor(String currentUser, String destinationUser) {
        return peopleNodeRepository.distanceFactor(currentUser, destinationUser);
    }


}
