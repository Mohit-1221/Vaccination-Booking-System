package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enums.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {
    String name;
    String emailId;
    String mobNo;
    CenterResponseDto centerResponseDto;

}
