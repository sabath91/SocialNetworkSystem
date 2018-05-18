package pl.czyz.springbootmongo.domain;

import java.time.LocalDateTime;


public class UserMessage {

    private final LocalDateTime dateOfPublication;
    private final String content;


    public UserMessage(String content) {
        this.dateOfPublication = LocalDateTime.now();
        this.content = content;
    }

    public LocalDateTime getDateOfPublication() {
        return dateOfPublication;
    }

    public String getContent() {
        return content;
    }
}
