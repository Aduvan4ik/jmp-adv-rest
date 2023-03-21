package com.jmp.cloud.business;

import com.jmp.cloud.entity.User;
import com.jmp.cloud.exception.EntityNotFoundException;
import com.jmp.cloud.mapper.UserMapper;
import com.jmp.cloud.repository.UserRepository;
import com.jmp.cloud.service.UserService;
import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto getUserDto(Long id) {
        return userMapper.mapToDto(getUser(id));
    }

    @Override
    public List<UserResponseDto> getAllUsersDto() {
        return userMapper.mapToDtos(userRepository.findAll());
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return userMapper.mapToDto(userRepository.save(userMapper.mapToEntity(userRequestDto)));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = getUser(id);
        user = userMapper.updateEntity(user, userRequestDto);
        return userMapper.mapToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with next id is not found: " + id));

    }

}
