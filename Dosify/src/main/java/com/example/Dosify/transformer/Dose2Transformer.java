package com.example.Dosify.transformer;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;

import java.util.UUID;

public class Dose2Transformer {
    public Dose2 create(User user, VaccineType vaccineType){
        return Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
