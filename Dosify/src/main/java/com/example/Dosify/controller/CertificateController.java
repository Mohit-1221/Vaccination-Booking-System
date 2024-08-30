package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDTO.CertificateRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;
    @PostMapping("/generatePDF")
    public ResponseEntity generateCertificate(@RequestBody CertificateRequestDto certificateRequestDto){
        try{
            CertificateResponseDto certificateResponseDto = certificateService.generateCertificate(certificateRequestDto);
            return new ResponseEntity(certificateResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
