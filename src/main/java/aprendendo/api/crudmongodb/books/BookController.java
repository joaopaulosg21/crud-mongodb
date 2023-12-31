package aprendendo.api.crudmongodb.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aprendendo.api.crudmongodb.common.Response;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<Response<Book>> create(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @GetMapping()
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Response<Book>> update(@RequestBody UpdateBookDTO updateBookDTO, @PathVariable String bookId) {
        return ResponseEntity.ok(bookService.updateBook(bookId, updateBookDTO));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Response<Book>> delete(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }
}
