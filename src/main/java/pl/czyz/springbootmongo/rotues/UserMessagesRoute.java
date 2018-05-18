package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

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
                    .to("bean:messageService?method=publishMessage(${header.login}, ${body})");




//      @Formatter:on
    }
}
