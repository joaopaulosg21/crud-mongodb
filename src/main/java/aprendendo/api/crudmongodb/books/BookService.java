package aprendendo.api.crudmongodb.books;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import aprendendo.api.crudmongodb.common.Response;
import aprendendo.api.crudmongodb.exceptions.BookNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public Response<Book> create(Book book) {
        Book saved = bookRepository.save(book);

        return new Response<Book>(saved, "Book successfully registered");
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Response<Book> updateBook(String bookId, UpdateBookDTO updateBookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new BookNotFoundException();
        }

        Book book = optionalBook.get();
        book.setTitle(updateBookDTO.title());
        book.setAuthor(updateBookDTO.author());
        book.setDescription(updateBookDTO.description());
        Book updated = bookRepository.save(book);

        return new Response<Book>(updated, "Book successfully updated");
    }

    public Response<Book> deleteBook(String bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new BookNotFoundException();
        }

        Book book = optionalBook.get();

        bookRepository.delete(book);

        return new Response<Book>(book, "Book successfully deleted");
    }
}
