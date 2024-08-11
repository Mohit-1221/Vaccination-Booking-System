package com.example.Dosify.service;

import com.example.Dosify.Enums.DoseNo;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;

import java.util.List;

public interface UserService {
    String deleteUserById(int id);

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    List<String> getAllByEmailId(String emailId);

    User getUserById(int id);

}
