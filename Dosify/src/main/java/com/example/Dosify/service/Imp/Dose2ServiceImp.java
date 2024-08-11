package com.example.Dosify.service.Imp;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;
import com.example.Dosify.service.Dose2Service;
import com.example.Dosify.transformer.Dose2Transformer;
import org.springframework.stereotype.Service;

@Service
public class Dose2ServiceImp implements Dose2Service {

    @Override
    public Dose2 createDose2(User user, VaccineType vaccineType) {
        return new Dose2Transformer().create(user,vaccineType);
    }
}
