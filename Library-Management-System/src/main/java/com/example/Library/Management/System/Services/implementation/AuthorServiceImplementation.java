package com.example.Library.Management.System.Services.implementation;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorServiceImplementation implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public ResponseEntity<String> addAuthor(Author author) {
        authorRepository.save(author);
        return ResponseEntity.ok("Author has been added to the DB!!!");
    }

    @Override
    public Author getAuthor(Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) {
            throw new Exception("This Id is an invalid entry");
        }
        return optionalAuthor.get();
    }

    @Override
    public List<String> getAllAuthorNames() {
        List<Author> authors = authorRepository.findAll();
        List<String> authorNames = new ArrayList<>();
        for (Author author : authors) {
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    @Override
    public List<String> getBookNameList(Integer authorId) {
        List<String> bookNames = new ArrayList<>();
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author != null) {
            List<Book> bookList = author.getBookList();
            for (Book book : bookList) {
                bookNames.add(book.getBookName());
            }
        }
        return bookNames;
    }
}
