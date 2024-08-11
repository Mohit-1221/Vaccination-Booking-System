package com.example.Dosify.service;

import com.example.Dosify.Enums.VaccineType;
import com.example.Dosify.model.Dose2;
import com.example.Dosify.model.User;

public interface Dose2Service {
    Dose2 createDose2(User user, VaccineType vaccineType);
}
