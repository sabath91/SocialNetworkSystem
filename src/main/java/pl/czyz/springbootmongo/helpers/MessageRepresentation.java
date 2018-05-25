package pl.czyz.springbootmongo.helpers;


import pl.czyz.springbootmongo.domain.User;
import pl.czyz.springbootmongo.domain.UserMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageRepresentation implements Comparable {

    private final String userName;
    private final String userSurname;
    private final LocalDateTime publicationDate;
    private final String content;

    public MessageRepresentation(UserMessage userMessage, User user) {
        this.userName = user.getName();
        this.userSurname = user.getSurname();
        this.publicationDate = LocalDateTime.parse(userMessage.getDateOfPublication());
        this.content = userMessage.getContent();
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getPublicationDate() {
        return publicationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(Object o) {
        LocalDateTime compareTime = ((MessageRepresentation) o).publicationDate;
        if (this.publicationDate.isEqual(compareTime)) return 0;
        if (this.publicationDate.isBefore(compareTime)) {
            return 1;
        } else {
            return -1;
        }
    }
}
