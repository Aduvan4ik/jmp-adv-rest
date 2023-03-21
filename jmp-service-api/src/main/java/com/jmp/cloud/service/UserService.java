package com.jmp.cloud.service;

import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto getUserDto(Long id);

    List<UserResponseDto> getAllUsersDto();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);
}
