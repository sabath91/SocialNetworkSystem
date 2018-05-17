package pl.czyz.springbootmongo.domain;

import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
@Data
public class PersonNode {

    private final String mongoId;
    @GraphId
    private Long id;
    @Relationship(direction = Relationship.INCOMING)
    private Set<PersonNode> invitations;

    @Relationship(direction = Relationship.UNDIRECTED)
    private Set<PersonNode> friends;

}
