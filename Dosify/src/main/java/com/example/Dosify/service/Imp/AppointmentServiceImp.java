package com.example.Dosify.service.Imp;

import com.example.Dosify.Enums.DoseNo;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.exception.NotEligibleForDoseException;
import com.example.Dosify.exception.UserNotFountException;
import com.example.Dosify.model.*;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.service.Dose2Service;
import com.example.Dosify.transformer.AppointmentTransformer;
import com.example.Dosify.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose1Service dose1Service;
    @Autowired
    Dose2Service dose2Service;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFountException, DoctorNotFoundException, NotEligibleForDoseException {

        Optional<User> userOptional = userRepository.findById(appointmentRequestDto.getUserId());
        if(!userOptional.isPresent()){
            throw new UserNotFountException("User doesn't exist!!");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!doctorOptional.isPresent()){
            throw new DoctorNotFoundException("Doctor doesn't exist!");
        }

        User user = userOptional.get();
        Doctor doctor = doctorOptional.get();

        if(appointmentRequestDto.getDoseNo() == DoseNo.DOSE_1){
            Dose1 dose1 = dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }
        else{
            if(!user.isDose1Taken()){
                throw new NotEligibleForDoseException("Sorry! You are not yet eligible for Dose 2");
            }
            Dose2 dose2 = dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }

        // create object
        Appointment appointment = AppointmentTransformer.bookedAppointment(appointmentRequestDto,user,doctor);
        user.getAppointments().add(appointment);
        User savedUser  = userRepository.save(user);   // save dose1/dose2 and appointment

        Appointment savedAppointment = savedUser.getAppointments().get(savedUser.getAppointments().size()-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        // SEND EMAIL;
//        String text = "Congrats! \n"+ user.getName() + " Your\n " + appointmentRequestDto.getDoseNo() +
//                  "Subject: Vaccination Appointment Confirmation\n" + "Dear ["+user.getName()+"],";
         String text = "Subject: Vaccination Appointment Confirmation\n\n"
                 + "Dear " + user.getName() + ",\n\n"
                 + "We are pleased to inform you that your vaccination appointment has been successfully booked. Here are the details of your appointment:\n\n"
                 + "Appointment Details:\n"
                 + "Your Dose: "+appointmentRequestDto.getDoseNo()+ "\n"
                 + "Vaccine Type: " + appointmentRequestDto.getVaccineType() + "\n"
                 + "Date and Time: " + savedAppointment.getDateOfAppointment()+ "\n"
                 + "Please make sure to bring a valid ID and your appointment confirmation code: " + savedAppointment.getAppointmentNo() + ".\n\n"
                 + "Thank you for taking this important step to protect your health and the health of those around you.\n\n"
                 + "Best regards,\n\n"
                 + "Dosify\n";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mohitme1221gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked!!");
        message.setText(text);
        emailSender.send(message);

        // prepare response dto
        return AppointmentTransformer.prepareResponseDto(appointmentRequestDto,doctor,user,savedAppointment);
    }
}
