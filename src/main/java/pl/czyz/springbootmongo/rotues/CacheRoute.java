package pl.czyz.springbootmongo.rotues;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:saveInCache")
                .setHeader("CamelEhcacheAction", constant("PUT"))
                .process(setCacheKey())
                .to("ehcache://cache");

        from("direct:getFromCache")
                .setHeader("CamelEhcacheAction", constant("GET"))
                .process(setCacheKey())
                .to("ehcache://cache")
                .choice()
                .when(exchange -> exchange.getIn().getBody().equals(""))
                .process(exchange -> exchange.getOut().setBody("Not ready yet"));

    }

    private Processor setCacheKey() {
        return exchange -> {
            String currentUser = (String) exchange.getIn().getHeader("login");
            String desinationUser = (String) exchange.getIn().getHeader("destinationUser");

            exchange.getIn().setHeader("CamelEhcacheKey", currentUser + "-" + desinationUser);
        };
    }
}
