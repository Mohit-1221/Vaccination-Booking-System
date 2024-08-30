package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.CertificateRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.exception.NotEligibleForCertification;
import com.example.Dosify.exception.UserNotFountException;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;

public interface CertificateService {
    CertificateResponseDto generateCertificate(CertificateRequestDto certificateRequestDto) throws UserNotFountException, NotEligibleForCertification, MessagingException, DocumentException;
}
