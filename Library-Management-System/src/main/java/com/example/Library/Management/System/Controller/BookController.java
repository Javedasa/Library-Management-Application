package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Enums.Genre;
import com.example.Library.Management.System.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addbook(@RequestBody Book book, @RequestParam("authorId")Integer authorId){

        try{
            String result=bookService.addBook(book,authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBooksByGenre")
    public List<String> getBooksByGenre(@RequestParam("genre") Genre genre){
        List<String>books=bookService.getBooksByGenre(genre);
        return books;
    }
}
