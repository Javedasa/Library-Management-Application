package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Author;
import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author has been added to the DB!!!";
    }

    public Author getAuthor(Integer authorId) throws Exception {
             Optional<Author> optionalAuthor= authorRepository.findById(authorId);
             if(!optionalAuthor.isPresent()){
                 throw new Exception("This Id is invalid entry");
             }
             Author author= optionalAuthor.get();
             return author;
    }

    public List<String> getAllAuthorNames() {
        List<Author> authors=authorRepository.findAll();
        List<String>authorNames=new ArrayList<>();
        for(Author author:authors){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public List<String> getBookNameList(Integer authorId) {
        List<String>bookNames=new ArrayList<>();
        Author author=authorRepository.findById(authorId).get();
        List<Book>bookList=author.getBookList();
        for(Book book:bookList){
            bookNames.add(book.getBookName());
        }
        return bookNames;
    }
}
