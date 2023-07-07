package aprendendo.api.crudmongodb.books;

import org.springframework.stereotype.Service;

import aprendendo.api.crudmongodb.common.Response;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public Response<Book> create(Book book) {
        Book saved = bookRepository.save(book);

        return new Response<Book>(saved, "Book successfully registered ");
    }
}
