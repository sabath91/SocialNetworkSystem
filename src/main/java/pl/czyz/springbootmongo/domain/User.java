package pl.czyz.springbootmongo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Document
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String login;
    private String name;
    private String surname;
    private String city;
    private LocalDate dateOfBirth;
    private List<UserMessage> publishedMessages = new ArrayList<>();


    public User(String name, String surname, String city, String login, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
    }

    public String getDateOfBirth() {
        return dateOfBirth.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public List<UserMessage> getPublishedMessages() {
        return Collections.unmodifiableList(publishedMessages);
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = LocalDate.parse(date);
    }

    public void publishMessage(UserMessage message) {
        publishedMessages.add(message);
    }
}
