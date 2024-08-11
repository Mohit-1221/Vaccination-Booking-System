package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;

public class CenterTransformer {
    public static VaccinationCenter RequestDtoToCenterEntity(CenterRequestDto centerRequestDto){
        return VaccinationCenter.builder().name(centerRequestDto.getName())
                .location(centerRequestDto.getLocation())
                .centerType(centerRequestDto.getCenterType())
                .build();
    }

    public static CenterResponseDto CenterEntityToResponseDto(VaccinationCenter vaccinationCenter){
        return CenterResponseDto.builder().name(vaccinationCenter.getName())
                .location(vaccinationCenter.getLocation())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }
}
