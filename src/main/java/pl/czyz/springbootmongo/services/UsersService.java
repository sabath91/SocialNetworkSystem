package pl.czyz.springbootmongo.services;

import pl.czyz.springbootmongo.domain.User;

import java.util.List;


public interface UsersService {


    List<User> findAllByCity(String city);

    List<User> findAllByName(String name);

    List<User> findAllByNameContaining(String partOfName);

    List<User> findAllBySurname(String surname);

    List<User> findAllBySurnameContaining(String partOfSurname);

    List<User> findAllByNameAndSurname(String name, String surname);

    List<User> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfsurname);

    List<User> findAllByNameAndSurnameAndCity(String name, String surname, String city);

    List<User> findAllByDateOfBirthIsBetween(int moreThen, int lessThen);

    List<User> findAll();

    User save(User user);

}
