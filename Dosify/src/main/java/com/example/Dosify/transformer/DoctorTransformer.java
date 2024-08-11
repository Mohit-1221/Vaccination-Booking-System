package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;

public class DoctorTransformer {
    public static Doctor RequestDtoToDoctorEntity(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .mobNo(doctorRequestDto.getMobNo())
                .gender(doctorRequestDto.getGender())
                .emailId(doctorRequestDto.getEmailId())
                .build();
    }

    public static DoctorResponseDto DoctorEntityToResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto = CenterTransformer.CenterEntityToResponseDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .mobNo(doctor.getMobNo())
                .emailId(doctor.getEmailId())
                .centerResponseDto(centerResponseDto)
                .build();
    }
}
