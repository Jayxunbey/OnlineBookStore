package uz.pdp.online.repo;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import uz.pdp.online.entity.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class BooksDatabase {
    private List<Book> books = new ArrayList<>(
            /*List.of(
                    new Book("1","Jayxunbey's Love", "Drama","2024","https://www.freeiconspng.com/uploads/blue-book-png-31.png"),
                    new Book("2","Tohir va Zuhra", "Sevgi","2018","https://www.freeiconspng.com/uploads/blue-book-png-31.png"),
                    new Book("3","Atom Odatlar", "Drama","2013","https://www.freeiconspng.com/uploads/blue-book-png-31.png"),
                    new Book("4","Java dasturlash", "Sevgi","2020","https://www.freeiconspng.com/uploads/blue-book-png-31.png"),
                    new Book("5","English Grammar", "Ilm","2010","https://www.freeiconspng.com/uploads/blue-book-png-31.png")
                    )*/);

}