package com.example.Dosify.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String certificateNo; // UUID

    @CreationTimestamp
    @Column(name = "certificate_Issued_Date")
    Date certificateIssuedDate;

    @OneToOne
    @JoinColumn
    User user;
}
