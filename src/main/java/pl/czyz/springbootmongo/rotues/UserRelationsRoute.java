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

                .post("/acceptInvitation/{invitationSender}")
                    .to("bean:relationsService?method=acceptInvitation(${header.invitationSender}, ${header.login})")

                .post("/declineInvitation/{invitationSender}")
                    .to("bean:relationsService?method=declineInvitation(${header.invitationSender}, ${header.login}")

                .post("deleteFriendship/{friendLogin}")
                    .to("bean:relationsService?method=deleteFriendship(${header.login}, ${header.friendLogin}")

                .get("/myFriends")
                    .to("bean:relationsService?method=myFriends(${header.login})")

                .get("/myNetwork")
                    .to("bean:relationsService?method=myNetwork(${header.login})")

                .get("/distanceTo/{destinationUser}")
                    .to("direct:getDistance");

                from("direct:getDistance")
                    .to("activemq:queue:distance");


                from("activemq:queue:distance?concurrentConsumers=10")
                    .to("bean:relationsService?method=distanceFactor(${header.login}, ${header.destinationUser})")
                    .to("direct:getLogs");

                from("direct:getLogs")
                        .to("bean:relationsService?method=hello(${body})");

//        @formatter:on
    }
}
