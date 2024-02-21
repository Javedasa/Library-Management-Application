package com.example.Library.Management.System.Services;

import com.example.Library.Management.System.Entities.Book;
import com.example.Library.Management.System.Entities.LibraryCard;
import com.example.Library.Management.System.Entities.Transaction;
import com.example.Library.Management.System.Enums.CardStatus;
import com.example.Library.Management.System.Enums.TransactionStatus;
import com.example.Library.Management.System.Exceptions.*;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;

    private static final Integer FINE_PER_DAY = 5;
    public String issueBook(Integer bookId, Integer cardId) throws Exception{
        Transaction transaction=new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        //validations
        //valid BookId
        Optional<Book>bookOptional=bookRepository.findById(bookId);
        if(!bookOptional.isPresent()) throw new BookNotFound("BookId entered is Invalid");

        Book book= bookOptional.get();
        //check is this available or not

        if(!book.isAvailable()) throw new BookNotAvailableException("BookId entered is Invalid");


        //to check valid cardId or not
        Optional<LibraryCard>optionalLibraryCard=cardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent()) throw new CardNotFound("cardId entered is Invalid");


        LibraryCard libraryCard=optionalLibraryCard.get();

        //check status of the card is it active or not
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("card status is not Active");
        }

        //maximum num of books Issues :Max limit=3
        if(libraryCard.getNumOfBooksIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxBooksAlreadyIssued(MAX_LIMIT_OF_BOOKS+" is maximum books that can be issued");
        }

        //creating transaction history
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
       libraryCard.setNumOfBooksIssued(libraryCard.getNumOfBooksIssued()+1);
       book.setAvailable(false);

       //setting Foreign Key
        transaction.setBook(book);
        transaction.setCard(libraryCard);

        //saving relevant information:bidirectional Mapping
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        //instead of saving the parent:just save the child
        transactionRepository.save(transaction);

        return "The book with boofId"+bookId+"has been issued"+"to card with"+cardId;
    }

    public String returnBook(Integer bookId, Integer cardId) {
        Book book = bookRepository.findById(bookId).get();
        LibraryCard card = cardRepository.findById(cardId).get();

        //I need to find out that issue Transaction

        Transaction transaction = transactionRepository.findTransactionByBookAndCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);

        Date issueDate = transaction.getCreatedOn();

        //Predefined method that you can use to calculate days
        long milliSeconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        Long days = TimeUnit.DAYS.convert(milliSeconds, TimeUnit.MILLISECONDS);

        int fineAmount = 0;

        if(days>15){
            fineAmount = Math.toIntExact((days - 15) * FINE_PER_DAY);
        }

        Transaction newTransaction = new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //Setting the FK's
        newTransaction.setBook(book);
        newTransaction.setCard(card);

        book.setAvailable(true);
        card.setNumOfBooksIssued(card.getNumOfBooksIssued()-1);


        book.getTransactionList().add(newTransaction);
        card.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "book has been returned";
    }
}
