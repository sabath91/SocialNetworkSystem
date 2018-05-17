package pl.czyz.springbootmongo.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.czyz.springbootmongo.domain.PersonNode;

import java.util.List;

public interface PeopleNodeRepository extends Neo4jRepository<PersonNode, Long> {
    PersonNode findByLogin(String login);

    @Query("MATCH (me:PersonNode {name:{0}})-[:FRIENDS_WITH]-(personNode:PersonNode) RETURN personNode")
    List<PersonNode> findAllFriends(String name);

    @Query("MATCH (me:PersonNode {name:{0}})-[:FRIENDS_WITH*]-(personNode:PersonNode) RETURN personNode")
    List<PersonNode> findNetwork(String name);

}
