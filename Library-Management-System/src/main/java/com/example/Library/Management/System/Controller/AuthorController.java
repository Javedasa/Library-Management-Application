package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
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
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        String result=authorService.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getAuthor")
    public ResponseEntity getAuthor(@RequestParam("authorId")Integer authorId){
        try{
            Author author=authorService.getAuthor(authorId);
            return new ResponseEntity(author , HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAllAuthorNames")
    public List<String> getAllAuthorNames(){
       return authorService.getAllAuthorNames();
    }

    @GetMapping("/getBookNameList")
    public ResponseEntity getBookNameList(@RequestParam("authorId")Integer authorId){
        List<String>bookNames=authorService.getBookNameList(authorId);
        return new ResponseEntity(bookNames,HttpStatus.OK);
    }
}
