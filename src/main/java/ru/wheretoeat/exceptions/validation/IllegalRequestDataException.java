package ru.wheretoeat.exceptions.validation;

public class IllegalRequestDataException extends RuntimeException{
    public IllegalRequestDataException(String msg) {
        super(msg);
    }
}
