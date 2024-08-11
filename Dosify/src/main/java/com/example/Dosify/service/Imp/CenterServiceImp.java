package com.example.Dosify.service.Imp;

import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.CenterService;
import com.example.Dosify.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterServiceImp implements CenterService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto){
        // dto to entity
        VaccinationCenter vaccinationCenter = CenterTransformer.RequestDtoToCenterEntity(centerRequestDto);

        // save the object in db
        VaccinationCenter savedCenter = vaccinationCenterRepository.save(vaccinationCenter);

        // entity to dto
        CenterResponseDto centerResponseDto = CenterTransformer.CenterEntityToResponseDto(savedCenter);

        return centerResponseDto;
    }
}
