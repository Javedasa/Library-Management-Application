package com.example.Library.Management.System.Entities;

import com.example.Library.Management.System.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="library_card")
public class LibraryCard {
    @Id
    private Integer cardNo;

    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private int numOfBooksIssued;

    //one to one mapping with studentTable

    @OneToOne
    @JoinColumn
    private Student student;     //This is acting as a FK of the Library Card table
    //This variable is to be put in mappedBy attribute in the parent class

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public int getNumOfBooksIssued() {
        return numOfBooksIssued;
    }

    public void setNumOfBooksIssued(int numOfBooksIssued) {
        this.numOfBooksIssued = numOfBooksIssued;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
