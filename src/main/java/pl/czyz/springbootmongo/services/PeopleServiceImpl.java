package pl.czyz.springbootmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.czyz.springbootmongo.domain.Person;
import pl.czyz.springbootmongo.repository.PeopleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "peopleService")
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public List<Person> findAllByCity(String city) {

        return peopleRepository.findAllByCity(city).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByName(String name) {
        return peopleRepository.findAllByName(name).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByNameContaining(String partOfName) {
        return peopleRepository.findAllByNameContaining(partOfName).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllBySurname(String surname) {
        return peopleRepository.findAllBySurname(surname).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllBySurnameContaining(String partOfSurname) {
        return peopleRepository.findAllBySurnameContaining(partOfSurname).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByNameAndSurname(String name, String surname) {
        return peopleRepository.findAllByNameAndSurname(name, surname).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByNameContainingAndSurnameContaining(String partOfName, String partOfSurname) {
        return peopleRepository.findAllByNameContainingAndSurnameContaining(partOfName, partOfSurname).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByNameAndSurnameAndCity(String name, String surname, String city) {
        return peopleRepository.findAllByNameAndSurnameAndCity(name, surname, city).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAllByDateOfBirthIsBetween(int olderThen, int youngerThen) {
        LocalDate currentDate = LocalDate.now();
        int yearFrom = currentDate.getYear() - youngerThen;
        int yearTo = currentDate.getYear() - olderThen;

        int month = currentDate.getMonth().getValue();
        int day = currentDate.getDayOfMonth();

        return peopleRepository.findAllByDateOfBirthIsBetween(LocalDate.of(yearFrom, month, day), LocalDate.of(yearTo, month, day)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return peopleRepository.save(person);
    }

}

