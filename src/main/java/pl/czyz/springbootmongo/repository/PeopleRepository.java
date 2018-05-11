package pl.czyz.springbootmongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import pl.czyz.springbootmongo.domain.Person;

import java.time.LocalDate;
import java.util.stream.Stream;


public interface PeopleRepository extends MongoRepository<Person, String> {

    Stream<Person> findAllByCity(String city);

    Stream<Person> findAllByName(String name);

    Stream<Person> findAllByNameContaining(String partOfName);

    Stream<Person> findAllBySurname(String surname);

    Stream<Person> findAllBySurnameContaining(String partOfSurname);

    Stream<Person> findAllByNameAndSurname(String name, String surname);

    Stream<Person> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfsurname);

    Stream<Person> findAllByNameAndSurnameAndCity(String name, String surname, String city);

    Stream<Person> findAllByDateOfBirthIsBetween(LocalDate moreThen, LocalDate lessThen);


}
