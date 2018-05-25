package pl.czyz.springbootmongo.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserMessage {

    private final LocalDateTime dateOfPublication;
    private final String content;


    public UserMessage(String content) {
        this.dateOfPublication = LocalDateTime.now();
        this.content = content;
    }

    public String getDateOfPublication() {
        return dateOfPublication.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }


//    public LocalDateTime getDateOfPublication() {
//        return dateOfPublication;
//    }

    public String getContent() {
        return content;
    }
}
