package com.example.Dosify.exception;

import com.example.Dosify.repository.DoctorRepository;

import java.util.Date;

public class DoctorNotFoundException extends Exception{
    public DoctorNotFoundException(String message){
        super(message);
    }
}
