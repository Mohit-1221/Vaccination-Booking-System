package com.example.Dosify.model;

import com.example.Dosify.Enums.CenterType;
import com.example.Dosify.Enums.VaccineType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "full_name")
    String name;

    @Column(name = "location")
    String location;

    @Column(name = "center_type")
    @Enumerated(EnumType.STRING)
    CenterType centerType;

    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
   List<Doctor> doctors = new ArrayList<>();
}
