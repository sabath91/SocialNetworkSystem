package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.domain.Person;

@Component
public class ExternalRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("restlet")
                .host("0.0.0.0")
                .port("8080")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                // turn on swagger api-doc
                .apiContextPath("/api/api-doc")
                .apiProperty("api.title", "People API")
                .apiProperty("api.version", "1.0.0");

//      @formatter:off
        rest("/api")
                .consumes("application/json")
                .produces("application/json")

                .get("/people")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAll")

                .get("/find/byCity")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByCity(${header.city})")

                .get("/find/byName")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByName(${header.name})")

                .get("/find/byNameContaining")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByNameContaining(${header.partOfName})")

                .get("/find/bySurname")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllBySurname(${header.surname})")

                .get("/find/bySurnameContaining")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllBySurnameContaining(${header.partOfSurname})")

                .get("/find/byNameAndSurname")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllBySurnameContaining(${header.name}{header.surname})")

                .get("/find/byNameContainingAndSurnameContaining")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByNameContainingAndSurnameContaining(${header.partOfName}{header.partOfSurname})")

                .get("/find/byNameSurnameAndCity")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByNameAndSurnameAndCity(${header.name}{header.surname}{header.city})")

                .get("/find/inAgeBetween")
                    .outType(Person[].class)
                    .responseMessage()
                    .code(200)
                    .endResponseMessage()
                    .to("bean:peopleServiceImpl?method=findAllByDateOfBirthIsBetween(${header.olderThen},${header.youngerThen})");


//        @formatter.:on

    }
}
