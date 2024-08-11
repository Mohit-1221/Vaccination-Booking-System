package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.exception.NotEligibleForDoseException;
import com.example.Dosify.exception.UserNotFountException;

public interface AppointmentService {

    AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFountException, DoctorNotFoundException, NotEligibleForDoseException;
}
