package com.jmp.cloud.mapper;

import com.jmp.cloud.entity.User;
import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto mapToDto(User user);

    List<UserResponseDto> mapToDtos(List<User> userList);

    User mapToEntity(UserRequestDto userRequestDto);

    User updateEntity(@MappingTarget User user, UserRequestDto userRequestDto);

}
