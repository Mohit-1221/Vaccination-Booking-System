package com.example.Dosify.transformer;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;

import java.util.UUID;

public class Dose1Transformer {
    public Dose1 createDose1(User user,VaccineType vaccineType){
        return Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
