package uz.pdp.online.entity;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    private String id;
    private String name;
    private String genre;
    private String year;
    private FileDetailes photo;
    private FileDetailes file;
    private long size;

    public Book(String name, String genre, String year) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.genre = genre;
        this.year = year;
    }
}
