package com.example.Dosify.service.Imp;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.transformer.Dose1Transformer;
import org.springframework.stereotype.Service;

@Service
public class Dose1ServiceImp implements Dose1Service {

    @Override
    public Dose1 createDose1(User user, VaccineType vaccineType) {
        return new Dose1Transformer().createDose1(user,vaccineType);
    }
}
