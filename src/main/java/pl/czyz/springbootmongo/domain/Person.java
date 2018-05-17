package pl.czyz.springbootmongo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document
@Data
@NoArgsConstructor
public class Person {

    @Id
    private String id;
    private String name;
    private String surname;
    private String city;
    private LocalDate dateOfBirth;


    public Person(String name, String surname, String city, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = LocalDate.parse(date);
    }
}
