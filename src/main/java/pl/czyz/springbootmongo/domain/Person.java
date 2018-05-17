package pl.czyz.springbootmongo.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document
@Data
public class Person {

    @Id
    private String id;
    @Indexed(unique = true)
    private final String login;
    private String name;
    private String surname;
    private String city;
    private LocalDate dateOfBirth;


    public Person(String name, String surname, String city, String login, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = LocalDate.parse(date);
    }
}
