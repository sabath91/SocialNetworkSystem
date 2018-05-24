package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RootRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .host("0.0.0.0")
                .port("8080")
                .bindingMode(RestBindingMode.json)
                // turn on swagger api-doc
                .apiContextPath("/api/api-doc")
                .apiProperty("api.title", "People API")
                .apiProperty("api.version", "1.0.0");

    }
}
