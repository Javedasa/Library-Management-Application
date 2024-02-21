package com.example.Library.Management.System.Exceptions;

public class InvalidCardStatusException extends Exception{

    public InvalidCardStatusException(String message) {
        super(message);
    }
}