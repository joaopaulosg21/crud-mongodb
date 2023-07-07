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

}
