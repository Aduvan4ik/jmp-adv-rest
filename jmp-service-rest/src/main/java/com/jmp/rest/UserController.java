package com.jmp.rest;

import io.swagger.annotations.*;
import com.jmp.dto.UserRequestDto;
import com.jmp.dto.UserResponseDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users/", produces = APPLICATION_JSON_VALUE)
@Api(tags = "user", value = "Users API", produces = APPLICATION_JSON_VALUE)
public interface UserController {

    @GetMapping(path = "{id}")
    @ApiOperation(value = "Get user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns UserResponseDto from db", response = UserResponseDto.class),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    ResponseEntity<UserResponseDto> getUser(@PathVariable(value = "id")
                                            @ApiParam(value = "User id", required = true, example = "1")
                                            Long id);

    @GetMapping
    @ApiOperation(value = "Get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns all UserResponseDto from db", response = UserResponseDto.class, responseContainer = "List")
    })
    ResponseEntity<CollectionModel<UserResponseDto>> getAllUser();

    @PostMapping
    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns saved UserResponseDto", response = UserResponseDto.class)
    })
    ResponseEntity<UserResponseDto> createUser(@RequestBody
                                               @ApiParam(value = "Data for creation new user", required = true)
                                               UserRequestDto userRequestDto);

    @PutMapping(path = "{id}")
    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns updated UserResponseDto", response = UserResponseDto.class),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    ResponseEntity<UserResponseDto> updateUser(@PathVariable(value = "id")
                                               @ApiParam(value = "User id", required = true, example = "1")
                                               Long id,
                                               @RequestBody
                                               @ApiParam(value = "Data for updating new user", required = true)
                                               UserRequestDto userRequestDto);

    @DeleteMapping(path = "{id}")
    @ApiOperation(value = "Get user")
    @ApiResponse(code = 204, message = "Deletes user from db")
    ResponseEntity<Void> deleteUser(@PathVariable(value = "id")
                                    @ApiParam(value = "User id", required = true, example = "1")
                                    Long id);
}

