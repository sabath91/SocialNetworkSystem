package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.repository.UsersRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "peopleService")
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public List<User> findAllByCity(String city) {

        return usersRepository.findAllByCity(city).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByName(String name) {
        return usersRepository.findAllByName(name).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByNameContaining(String partOfName) {
        return usersRepository.findAllByNameContaining(partOfName).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllBySurname(String surname) {
        return usersRepository.findAllBySurname(surname).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllBySurnameContaining(String partOfSurname) {
        return usersRepository.findAllBySurnameContaining(partOfSurname).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByNameAndSurname(String name, String surname) {
        return usersRepository.findAllByNameAndSurname(name, surname).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfSurname) {
        return usersRepository.findAllByNameContainingAndSurnameContaining(partOfName, partOfSurname).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByNameAndSurnameAndCity(String name, String surname, String city) {
        return usersRepository.findAllByNameAndSurnameAndCity(name, surname, city).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByDateOfBirthIsBetween(int olderThen, int youngerThen) {
        LocalDate currentDate = LocalDate.now();
        int yearFrom = currentDate.getYear() - youngerThen;
        int yearTo = currentDate.getYear() - olderThen;

        int month = currentDate.getMonth().getValue();
        int day = currentDate.getDayOfMonth();

        return usersRepository.findAllByDateOfBirthIsBetween(LocalDate.of(yearFrom, month, day), LocalDate.of(yearTo, month, day)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User save(User user) {
        return usersRepository.save(user);
    }

}

