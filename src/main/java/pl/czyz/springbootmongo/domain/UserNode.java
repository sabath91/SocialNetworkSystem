package pl.czyz.springbootmongo.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class UserNode {

    @GraphId
    private Long id;

    private String login;

    @Relationship(direction = Relationship.OUTGOING, type = "INVITED_BY")
    private Set<UserNode> invitations;

    @Relationship(direction = Relationship.UNDIRECTED, type = "FRIEND_WITH")
    private Set<UserNode> friends;

    public UserNode() {
    }

    public UserNode(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<UserNode> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<UserNode> invitations) {
        this.invitations = invitations;
    }

    public Set<UserNode> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserNode> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNode that = (UserNode) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login);
    }

    public void string() {
        invitations.forEach(i -> System.out.println(i.getLogin()));
//        friends.forEach(f-> System.out.println(f.getLogin())) ;
    }

    public void addInvitation(UserNode userNode) {
        if (invitations == null) {
            invitations = new HashSet<>();
        }
        invitations.add(userNode);
    }

    public void addFriend(UserNode userNode) {
        if (friends == null) {
            friends = new HashSet<>();
        }
        friends.add(userNode);
    }

}
