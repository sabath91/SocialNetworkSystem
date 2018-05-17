package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.Person;

import java.util.List;

public interface PeopleService {


    List<Person> findAllByCity(String city);

    List<Person> findAllByName(String name);

    List<Person> findAllByNameContaining(String partOfName);

    List<Person> findAllBySurname(String surname);

    List<Person> findAllBySurnameContaining(String partOfSurname);

    List<Person> findAllByNameAndSurname(String name, String surname);

    List<Person> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfsurname);

    List<Person> findAllByNameAndSurnameAndCity(String name, String surname, String city);

    List<Person> findAllByDateOfBirthIsBetween(int moreThen, int lessThen);

    List<Person> findAll();

    Person save(Person person);

    void saveAsNode(Person person);

}
