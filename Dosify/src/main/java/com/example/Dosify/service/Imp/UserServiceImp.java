package com.example.Dosify.service.Imp;

import com.example.Dosify.Enums.DoseNo;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.Dose1;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

        // convert request dto into entity
//        User user = new User();
//        user.setName(userRequestDto.getName());
//        user.setAge(userRequestDto.getAge());
//        user.setEmailId(userRequestDto.getEmailId());
//        user.setMobNo(userRequestDto.getMobNo());
//        user.setGender(userRequestDto.getGender());


        // using builder
        User user = UserTransformer.userRequestDtoToUser(userRequestDto);

        // save the object in db.
        User savedUser = userRepository.save(user);

        // convert entity into response dto
//        UserResponseDto userResponseDto = new UserResponseDto();
//        userResponseDto.setName(savedUser.getName());
//        userResponseDto.setMessage("Congrats! You have registered on Dosify");

        // using builder
        UserResponseDto userResponseDto = UserTransformer.userToUserResponseDto(savedUser);

        return  userResponseDto;
    }

    @Override
    public User getUserById(int id) {
       return userRepository.findById(id).get();
    }

    @Override
    public String deleteUserById(int id) {
        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public List<String> getAllByEmailId(String emailId) {
        List<User> users = userRepository.findByEmailId(emailId);
        List<String> userList = new ArrayList<>();
        for(User user : users){
            userList.add(user.getName());
        }
        return userList;
    }
}
