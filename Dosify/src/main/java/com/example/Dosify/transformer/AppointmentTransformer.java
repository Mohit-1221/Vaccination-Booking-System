package com.example.Dosify.transformer;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.User;
import com.example.Dosify.model.VaccinationCenter;

import java.util.UUID;

public class AppointmentTransformer {

    public static Appointment bookedAppointment(AppointmentRequestDto appointmentRequestDto, User user, Doctor doctor){
        return Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .build();
    }

    public static AppointmentResponseDto prepareResponseDto(AppointmentRequestDto appointmentRequestDto,Doctor doctor,User user,Appointment appointment){
        CenterResponseDto centerResponseDto = CenterTransformer.CenterEntityToResponseDto(doctor.getVaccinationCenter());
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .doctorName(doctor.getName())
                .centerResponseDto(centerResponseDto)
                .vaccineType(appointmentRequestDto.getVaccineType())
                .build();
    }
}
