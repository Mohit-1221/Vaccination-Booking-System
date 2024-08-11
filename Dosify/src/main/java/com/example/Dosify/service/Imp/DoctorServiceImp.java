package com.example.Dosify.service.Imp;

import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

       Optional<VaccinationCenter> optionalCenter = vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
       if(!optionalCenter.isPresent()){
           throw new CenterNotPresentException("Invalid center id!");
       }
       VaccinationCenter center = optionalCenter.get();

       // Dto to entity
        Doctor doctor = DoctorTransformer.RequestDtoToDoctorEntity(doctorRequestDto);
        doctor.setVaccinationCenter(center);

        //add doctor to current list of doctors to that center
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = vaccinationCenterRepository.save(center); // save both center and doctors

        // prepare responseDto
        DoctorResponseDto doctorResponseDto = DoctorTransformer.DoctorEntityToResponseDto(doctor);

        return doctorResponseDto;
    }
}
