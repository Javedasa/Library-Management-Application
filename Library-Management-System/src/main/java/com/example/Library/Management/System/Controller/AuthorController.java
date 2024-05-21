package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author) {
        ResponseEntity<String> response = authorService.addAuthor(author);
        return response;
    }

    @GetMapping("/getAuthor")
    public ResponseEntity<Author> getAuthor(@RequestParam("authorId") Integer authorId) {
        try {
            Author author = authorService.getAuthor(authorId);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllAuthorNames")
    public ResponseEntity<List<String>> getAllAuthorNames() {
        List<String> authorNames = authorService.getAllAuthorNames();
        return new ResponseEntity<>(authorNames, HttpStatus.OK);
    }

    @GetMapping("/getBookNameList")
    public ResponseEntity<List<String>> getBookNameList(@RequestParam("authorId") Integer authorId) {
        List<String> bookNames = authorService.getBookNameList(authorId);
        return new ResponseEntity<>(bookNames, HttpStatus.OK);
    }
}
