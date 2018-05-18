package pl.czyz.springbootmongo.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.czyz.springbootmongo.domain.PersonNode;

import java.util.List;
import java.util.Set;

public interface PeopleNodeRepository extends Neo4jRepository<PersonNode, Long> {
    PersonNode findByLogin(String login);

    @Query("MATCH (currentUser:PersonNode {login:{0}})-[:FRIEND_WITH]-(personNode:PersonNode) RETURN personNode")
    List<PersonNode> findAllFriends(String currentUser);

    @Query("MATCH (currentUser:PersonNode {login:{0}})-[:FRIEND_WITH*]-(personNode:PersonNode) RETURN personNode")
    List<PersonNode> findNetwork(String currentUser);

    @Query("MATCH (currentUser:PersonNode {login:{0}})-[r:INVITED_BY]-(other:PersonNode {login:{1}}) DELETE r CREATE (currentUser)-[:FRIEND_WITH]->(other)")
    void deleteInvitationAndMarkAsFriends(String currentUser, String invitationSender);

    @Query("MATCH (currentUser:PersonNode {login:{0}}),(invitationAddressee:PersonNode {login:{1}}) CREATE (currentUser)-[r:INVITED_BY]->(invitationAddressee)")
    void sendInvitation(String currentUser, String invitationAddressee);

    @Query("MATCH invitationsFor=(:PersonNode {login:{0}})-[r:INVITED_BY]-() RETURN invitationsFor")
    Set<PersonNode> findMyInvitations(String currentUser);

    @Query("MATCH (:PersonNode {login:{0}})-[r:INVITED_BY]-(:PersonNode {login:{1}}) DELETE r")
    void declineInvitation(String currentUser, String invitationSender);

    @Query("MATCH (:PersonNode {login:{0}})-[r:FRIEND_WITH]-(:PersonNode {login:{1]}) DELETE r")
    void deleteFriendship(String currentUser, String friendToDelete);

    @Query("MATCH (currentUser:PersonNode {login:{0}}), (destinationUser:PersonNode {login:{1}}), p= shortestPath((currentUser)-[:FRIEND_WITH*]-(destinationUser)) RETURN length(p)")
    Integer distanceFactor(String currentUser, String destinationUser);
}
