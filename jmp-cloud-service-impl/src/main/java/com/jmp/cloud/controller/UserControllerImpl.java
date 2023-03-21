package com.jmp.cloud.controller;

import com.jmp.rest.UserController;
import com.jmp.cloud.service.UserService;
import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users/", produces = APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private static final String EDIT_USER = "edit_user";
    private static final String DELETE_USER = "delete_user";
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseDto> getUser(Long id) {
        UserResponseDto userDto = userService.getUserDto(id);
        userDto.add(createLinksForUserDto(userDto));
        return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<CollectionModel<UserResponseDto>> getAllUser() {
        List<UserResponseDto> usersDtos = userService.getAllUsersDto();
        usersDtos.forEach(usersDto -> usersDto.add(createLinksForUserDto(usersDto)));
        CollectionModel<UserResponseDto> collectionModel = CollectionModel.of(usersDtos);
        collectionModel.add(linkTo(methodOn(UserControllerImpl.class).getAllUser()).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @Override
    public ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        userResponseDto.add(createLinksForUserDto(userResponseDto));
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(Long id, UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        userResponseDto.add(createLinksForUserDto(userResponseDto));
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private List<Link> createLinksForUserDto(UserResponseDto userResponseDto) {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(UserControllerImpl.class).slash(userResponseDto.getId()).withSelfRel());
        links.add(linkTo(UserControllerImpl.class).slash(userResponseDto.getId())
                .withRel(EDIT_USER)
                .withType(HttpMethod.PUT.name()));
        links.add(linkTo(UserControllerImpl.class).slash(userResponseDto.getId())
                .withRel(DELETE_USER)
                .withType(HttpMethod.DELETE.name()));
        return links;
    }
}
