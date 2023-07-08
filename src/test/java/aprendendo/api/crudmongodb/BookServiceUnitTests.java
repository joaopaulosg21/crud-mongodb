package aprendendo.api.crudmongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import aprendendo.api.crudmongodb.books.Book;
import aprendendo.api.crudmongodb.books.BookRepository;
import aprendendo.api.crudmongodb.books.BookService;
import aprendendo.api.crudmongodb.books.UpdateBookDTO;
import aprendendo.api.crudmongodb.common.Response;
import aprendendo.api.crudmongodb.exceptions.BookNotFoundException;

@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTests {
    
    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    private void setup() {
        this.bookService = new BookService(bookRepository);
    }

    @Test
    public void createBookTest() {
        Book book = new Book("test","test author","test description");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Response<Book> response = bookService.create(book);

        assertEquals("Book successfully registered",response.getMessage());
        assertEquals(book.getTitle(),response.getObject().getTitle());
    }

    @Test
    public void findAllBooksTest() {
        Book book = new Book("test","test author","test description");
        List<Book> lista = new ArrayList<>();
        lista.add(book);

        when(bookRepository.findAll()).thenReturn(lista);

        List<Book> response = bookService.findAll();

        assertEquals(book.getTitle(),response.get(0).getTitle());
    }

    @Test
    public void updateBookTest() {
        Book book = new Book("book_id","test", "test author","test description");
        String id = "book_id";

        UpdateBookDTO updateBookDTO = new UpdateBookDTO("New title","New description", "New author");

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Response<Book> response = bookService.updateBook(id, updateBookDTO);

        assertEquals("Book successfully updated", response.getMessage());
        assertEquals(updateBookDTO.title(),response.getObject().getTitle());
    }

    @Test
    public void updateBookExceptionTest() {
        UpdateBookDTO updateBookDTO = new UpdateBookDTO("New title","New description", "New author");
        String id = "book_id";

        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.updateBook(id, updateBookDTO));

        assertEquals("Book with this id not found", exception.getMessage());

    }

    @Test
    public void deleteBookTest() {
        Book book = new Book("book_id","test", "test author","test description");
        String id = "book_id";

        when(bookRepository.findById(anyString())).thenReturn(Optional.of(book));

        Response<Book> response = bookService.deleteBook(id);
        
        assertEquals("Book successfully deleted",response.getMessage());
        assertEquals(book.getId(),response.getObject().getId());

    }

    @Test
    public void deleteBookExceptionTest() {
        String id = "book_id";

        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(id));

        assertEquals("Book with this id not found", exception.getMessage());

    }
}
