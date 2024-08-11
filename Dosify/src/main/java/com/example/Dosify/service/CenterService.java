package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;

public interface CenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);
}
