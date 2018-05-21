package pl.czyz.springbootmongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import pl.czyz.springbootmongo.domain.User;

import java.time.LocalDate;
import java.util.stream.Stream;


public interface UsersRepository extends MongoRepository<User, String> {

    Stream<User> findAllByCity(String city);

    Stream<User> findAllByName(String name);

    Stream<User> findAllByNameContaining(String partOfName);

    Stream<User> findAllBySurname(String surname);

    Stream<User> findAllBySurnameContaining(String partOfSurname);

    Stream<User> findAllByNameAndSurname(String name, String surname);

    Stream<User> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfsurname);

    Stream<User> findAllByNameAndSurnameAndCity(String name, String surname, String city);

    Stream<User> findAllByDateOfBirthIsBetween(LocalDate moreThen, LocalDate lessThen);

    User findByLogin(String login);
}
