package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.CertificateRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.User;

import java.util.UUID;

public class CertificateTransformer {
    public static Certificate prepareCertificate(CertificateRequestDto certificateRequestDto, User user){
        return Certificate.builder()
                .certificateNo(String.valueOf(UUID.randomUUID()))
                .user(user)
                .build();
    }
    public static CertificateResponseDto prepareResponseDto(Certificate certificate){
        return CertificateResponseDto.builder()
                .certificateNo(certificate.getCertificateNo())
                .userName(certificate.getUser().getName())
                .build();
    }
}
