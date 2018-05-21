package pl.czyz.springbootmongo.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.czyz.springbootmongo.domain.UserNode;

import java.util.List;
import java.util.Set;

public interface UsersNodeRepository extends Neo4jRepository<UserNode, Long> {
    UserNode findByLogin(String login);

    @Query("MATCH (currentUser:UserNode {login:{0}})-[:FRIEND_WITH]-(personNode:UserNode) RETURN personNode")
    List<UserNode> findAllFriends(String currentUser);

    @Query("MATCH (currentUser:UserNode {login:{0}})-[:FRIEND_WITH*]-(personNode:UserNode) RETURN personNode")
    List<UserNode> findNetwork(String currentUser);

    @Query("MATCH (currentUser:UserNode {login:{0}})-[r:INVITED_BY]-(other:UserNode {login:{1}}) DELETE r CREATE (currentUser)-[:FRIEND_WITH]->(other)")
    void deleteInvitationAndMarkAsFriends(String currentUser, String invitationSender);

    @Query("MATCH (currentUser:UserNode {login:{0}}),(invitationAddressee:UserNode {login:{1}}) CREATE (currentUser)-[r:INVITED_BY]->(invitationAddressee)")
    void sendInvitation(String currentUser, String invitationAddressee);

    @Query("MATCH (:UserNode {login:{0}})<-[r:INVITED_BY]-(f:UserNode) RETURN f")
    Set<UserNode> findMyInvitations(String currentUser);

    @Query("MATCH (:UserNode {login:{0}})-[r:INVITED_BY]-(:UserNode {login:{1}}) DELETE r")
    void declineInvitation(String currentUser, String invitationSender);

    @Query("MATCH (:UserNode {login:{0}})-[r:FRIEND_WITH]-(:UserNode {login:{1]}) DELETE r")
    void deleteFriendship(String currentUser, String friendToDelete);

    @Query("MATCH (currentUser:UserNode {login:{0}}), (destinationUser:UserNode {login:{1}}), p= shortestPath((currentUser)-[:FRIEND_WITH*]-(destinationUser)) RETURN length(p)")
    Integer distanceFactor(String currentUser, String destinationUser);
}
