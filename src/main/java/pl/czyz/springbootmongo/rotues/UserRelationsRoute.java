package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserRelationsRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

//       @formatter:off
        rest("/api/user/{login}")
                .consumes("application/json")
                .produces("application/json")

                .get("/myInvitations")
                    .to("bean:relationsService?method=myInvitations(${header.login})")

                .post("/sendInvitation/{friendLogin}")
                    .to("bean:relationsService?method=sendInvitation(${header.login}, ${header.friendLogin})")

                .post("/acceptInvitation/{invitationsSender}")
                    .to("bean:relationsService?method=acceptInvitation(${header.invitationsSender}, ${header.login})")

                .post("/declineInvitation/{invitationSender}")
                    .to("bean:relationsService?method=declineInvitation(${header.invitationsSender}, ${header.login}")

                .post("deleteFriendship/{friendLogin}")
                    .to("bean:relationsService?method=deleteFriendship(${header.login}, ${header.friendLogin}")

                .get("/myFriends")
                    .to("bean:relationsService?method=myFriends(${header.login})")

                .get("/myNetwork")
                    .to("bean:relationsService?method=myNetwork(${header.login})");



//        @formatter:on
    }
}
