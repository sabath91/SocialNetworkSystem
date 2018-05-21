package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.helpers.MessageRepresentation;

@Component
public class UserMessagesRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {


//      @Formatter:off
        rest("/api/user/{login}/messages")
                .produces("application/json")
                .consumes("application/json")

                .get("/myMessages")
                    .to("bean:messageService?method=myMessages(${header.login})")

                .post("/newMessage")
                    .type(String.class)
                    .to("bean:messageService?method=publishMessage(${header.login}, ${body})")

                .get("/friendsMessages")
                    .outType(MessageRepresentation[].class)
                    .to("direct:getFriendsMessages")

                .get("/networkMessages")
                    .outType(MessageRepresentation[].class)
                    .to("direct:getNetworkMessages");


                from("direct:getFriendsMessages")
                    .to("bean:relationsService?method=myFriends(${header.login})")
                    .to("bean:messageService?method=friendsMessages(${body})");

                from("direct:getNetworkMessages")
                    .to("bean:relationsService?method=myNetwork(${header.login})")
                    .to("bean:messageService?method=networkMessages(${body})");


//      @Formatter:on
    }
}
