package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest("/user")
                .consumes("application/json")
                .produces("application/json");

    }
}
