package com.example.student;

public class StudentNotFoundError extends Error {

    /**
     * Serial version id
     */
    private static final long serialVersionUID = -1423213232641628353L;

    public StudentNotFoundError(String message) {
        super(message);
    }
}
