package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    ResponseEntity<String> addAuthor(Author author);
    Author getAuthor(Integer authorId) throws Exception;
    List<String> getAllAuthorNames();
    List<String> getBookNameList(Integer authorId);
}
