package aprendendo.api.crudmongodb.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
