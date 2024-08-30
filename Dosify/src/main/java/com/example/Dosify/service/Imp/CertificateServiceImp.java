package com.example.Dosify.service.Imp;


import com.example.Dosify.dto.RequestDTO.CertificateRequestDto;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.exception.NotEligibleForCertification;
import com.example.Dosify.exception.UserNotFountException;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.CertificateRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.CertificateService;
import com.example.Dosify.transformer.CertificateTransformer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class CertificateServiceImp implements CertificateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public CertificateResponseDto generateCertificate(CertificateRequestDto certificateRequestDto) throws UserNotFountException, NotEligibleForCertification, MessagingException, DocumentException {
        Optional<User> userOptional = userRepository.findById(certificateRequestDto.getUserId());
        if(!userOptional.isPresent()){
            throw new UserNotFountException("User doesn't exist!!");
        }
        User user = userOptional.get();
        if (!user.isDose1Taken() && !user.isDose2Taken()) {
            throw new NotEligibleForCertification("Sorry! You are not fully vaccinated and not yet eligible for Certificate");
        }
        Certificate certificate = CertificateTransformer.prepareCertificate(certificateRequestDto,user);
        Certificate savedCertificate = certificateRepository.save(certificate);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document();

            PdfWriter.getInstance(document,baos);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD,24);
            Paragraph titleParagraph = new Paragraph("Vaccination Certificate",font);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);

            Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
            Paragraph Paragraph = new Paragraph("This is to certify that",font1);
            Paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph);

            Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
            Paragraph Paragraph1 = new Paragraph(user.getName(),font2);
            Paragraph1.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph1);

            Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
            Paragraph Paragraph2 = new Paragraph("Has successfully taken both doses of the vaccine: ",font3);
            Paragraph2.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph2);

            Font font4 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);
            Paragraph Paragraph3 = new Paragraph("Dose1: "+user.getDose1().getVaccinationDate(),font4);
            Paragraph3.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph3);

            Font font5 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,16);
            Paragraph Paragraph4 = new Paragraph("Dose2: "+user.getDose2().getVaccinationDate(),font5);
            Paragraph4.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph4);

            Font font6 = FontFactory.getFont(FontFactory.HELVETICA_BOLD,14);
            Paragraph Paragraph5 = new Paragraph("Thank you for getting vaccinated!",font6);
            Paragraph5.setAlignment(Element.ALIGN_CENTER);
            document.add(Paragraph5);

//            // Add certificate title
//            document.add(new Paragraph("Vaccination Certificate").setFontSize(24).setBold());
//
//            // Add user name
//            document.add(new Paragraph("This is to certify that").setFontSize(18));
//            document.add(new Paragraph(user.getName()).setFontSize(20).setBold());
//
//            // Add dose details
//            document.add(new Paragraph("Has successfully taken both doses of the vaccine:").setFontSize(18));
////            document.add(new Paragraph("Dose 1: " + dose1Date).setFontSize(16));
////            document.add(new Paragraph("Dose 2: " + dose2Date).setFontSize(16));
//
//            // Add a thank you message or any additional details
//            document.add(new Paragraph("Thank you for getting vaccinated!").setFontSize(14));

            document.close();
        byte[] pdfBytes = baos.toByteArray();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("backenddosify@gmail.com");
        helper.setTo(user.getEmailId());
        helper.setSubject("Vaccination Certificate");
        helper.setText("Dear " + user.getName() + ",\n\nPlease find attached your vaccination certificate.\n\nBest regards,\nVaccination Center");

        // Attach the PDF
        helper.addAttachment("Vaccination_Certificate.pdf", new ByteArrayResource(pdfBytes));

        emailSender.send(message);

        return CertificateTransformer.prepareResponseDto(savedCertificate);

        }
}