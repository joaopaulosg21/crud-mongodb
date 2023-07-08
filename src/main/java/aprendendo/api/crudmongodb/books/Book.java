package aprendendo.api.crudmongodb.books;

import org.springframework.data.annotation.Id;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
public class Book {
    
    @Id
    private String id;

    private String title;

    private String author;

    private String description;

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

}
