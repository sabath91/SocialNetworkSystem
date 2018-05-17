package pl.czyz.springbootmongo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import pl.czyz.springbootmongo.domain.PersonNode;

public interface PeopleNodeRepository extends Neo4jRepository<PersonNode, Long> {
}
