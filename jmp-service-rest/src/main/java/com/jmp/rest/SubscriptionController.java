package com.jmp.rest;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/subscriptions/", produces = APPLICATION_JSON_VALUE)
@Api(tags = "subscription", value = "Subscriptions API", produces = APPLICATION_JSON_VALUE)
public interface SubscriptionController {

    @GetMapping(path = "{id}")
    @ApiOperation(value = "Get subscription")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns SubscriptionResponseDto from db", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription Not Found"),
    })
    ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable(value = "id")
                                            @ApiParam(value = "Subscription id", required = true, example = "1")
                                            Long id);

    @GetMapping
    @ApiOperation(value = "Get all subscriptions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns all SubscriptionResponseDto from db", response = SubscriptionResponseDto.class, responseContainer = "List")
    })
    ResponseEntity<CollectionModel<SubscriptionResponseDto>> getAllSubscription();

    @PostMapping
    @ApiOperation(value = "Create subscription")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns saved SubscriptionResponseDto", response = SubscriptionResponseDto.class)
    })
    ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody
                                               @ApiParam(value = "Data for creation new subscription", required = true)
                                               SubscriptionRequestDto subscriptionRequestDto);

    @PutMapping(path = "{id}")
    @ApiOperation(value = "Update subscription")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns updated SubscriptionResponseDto", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription or User Not Found"),
    })
    ResponseEntity<SubscriptionResponseDto> updateSubscription(@PathVariable(value = "id")
                                               @ApiParam(value = "Subscription id", required = true, example = "1")
                                               Long id,
                                               @RequestBody
                                               @ApiParam(value = "Data for updating new subscription", required = true)
                                               SubscriptionRequestDto subscriptionRequestDto);

    @DeleteMapping(path = "{id}")
    @ApiOperation(value = "Get subscription")
    @ApiResponse(code = 204, message = "Deletes subscription from db")
    ResponseEntity<Void> deleteSubscription(@PathVariable(value = "id")
                                    @ApiParam(value = "Subscription id", required = true, example = "1")
                                    Long id);
}

