package pl.czyz.springbootmongo.rotues;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import pl.czyz.springbootmongo.domain.Person;

@Component
public class PeopleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

//      @formatter:off
        rest("/api/people")
                .consumes("application/json")
                .produces("application/json")

                .get()
                    .to("bean:peopleService?method=findAll")

                .get("/find/byCity")
                    .to("bean:peopleService?method=findAllByCity(${header.city})")

                .get("/find/byName")
                    .to("bean:peopleService?method=findAllByName(${header.name})")

                .get("/find/byNameContaining")
                    .to("bean:peopleService?method=findAllByNameContaining(${header.partOfName})")

                .get("/find/bySurname")
                    .to("bean:peopleService?method=findAllBySurname(${header.surname})")

                .get("/find/bySurnameContaining")
                    .to("bean:peopleService?method=findAllBySurnameContaining(${header.partOfSurname})")

                .get("/find/byNameAndSurname")
                    .to("bean:peopleService?method=findAllBySurnameContaining(${header.name}{header.surname})")

                .get("/find/byNameContainingAndSurnameContaining")
                    .to("bean:peopleService?method=findAllByNameContainingAndSurnameContaining(${header.partOfName}{header.partOfSurname})")

                .get("/find/byNameSurnameAndCity")
                    .to("bean:peopleService?method=findAllByNameAndSurnameAndCity(${header.name}{header.surname}{header.city})")

                .get("/find/inAgeBetween")
                    .to("bean:peopleService?method=findAllByDateOfBirthIsBetween(${header.olderThen},${header.youngerThen})")

                .post()
                    .type(Person.class)
                    .responseMessage()
                    .code(201)
                    .endResponseMessage()
                    .to("direct:savePerson");

                from("direct:savePerson")
                        .enrich("direct:savePersonInMongoDb", (oldExchange, newExchange) -> newExchange)
                        .to("direct:savePersonNodeInNeo4j");

                from("direct:savePersonInMongoDb").to("bean:peopleService?method=save(${body})");

                from("direct:savePersonNodeInNeo4j").to("bean:relationsService?method=savePersonAsNode(${body})");

//        @formatter.:on

    }
}
