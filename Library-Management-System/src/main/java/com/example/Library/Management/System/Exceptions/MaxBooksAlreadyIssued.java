package com.example.Library.Management.System.Exceptions;

public class MaxBooksAlreadyIssued extends Exception{

    public MaxBooksAlreadyIssued(String message) {
        super(message);
    }
}