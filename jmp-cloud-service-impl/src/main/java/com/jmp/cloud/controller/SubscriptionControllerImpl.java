package com.jmp.cloud.controller;

import com.jmp.rest.SubscriptionController;
import com.jmp.cloud.service.SubscriptionService;
import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
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
@RequestMapping(value = "/api/v1/subscriptions/", produces = APPLICATION_JSON_VALUE)
public class SubscriptionControllerImpl implements SubscriptionController {

    private static final String EDIT_USER = "edit_subscription";
    private static final String DELETE_USER = "delete_subscription";
    private final SubscriptionService subscriptionService;

    public SubscriptionControllerImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> getSubscription(Long id) {
        SubscriptionResponseDto subscriptionDto = subscriptionService.getSubscriptionDto(id);
        subscriptionDto.add(createLinksForSubscriptionDto(subscriptionDto));
        return ResponseEntity.ok(subscriptionDto);
    }

    @Override
    public ResponseEntity<CollectionModel<SubscriptionResponseDto>> getAllSubscription() {
        List<SubscriptionResponseDto> subscriptionsDtos = subscriptionService.getAllSubscriptionsDto();
        subscriptionsDtos.forEach(subscriptionsDto -> subscriptionsDto.add(createLinksForSubscriptionDto(subscriptionsDto)));
        CollectionModel<SubscriptionResponseDto> collectionModel = CollectionModel.of(subscriptionsDtos);
        collectionModel.add(linkTo(methodOn(SubscriptionControllerImpl.class).getAllSubscription()).withSelfRel());
        return ResponseEntity.ok(collectionModel);
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.createSubscription(subscriptionRequestDto);
        subscriptionResponseDto.add(createLinksForSubscriptionDto(subscriptionResponseDto));
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @Override
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionResponseDto subscriptionResponseDto = subscriptionService.updateSubscription(id, subscriptionRequestDto);
        subscriptionResponseDto.add(createLinksForSubscriptionDto(subscriptionResponseDto));
        return ResponseEntity.ok(subscriptionResponseDto);
    }

    @Override
    public ResponseEntity<Void> deleteSubscription(Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    private List<Link> createLinksForSubscriptionDto(SubscriptionResponseDto subscriptionResponseDto) {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(SubscriptionControllerImpl.class).slash(subscriptionResponseDto.getId()).withSelfRel());
        links.add(linkTo(SubscriptionControllerImpl.class).slash(subscriptionResponseDto.getId())
                .withRel(EDIT_USER)
                .withType(HttpMethod.PUT.name()));
        links.add(linkTo(SubscriptionControllerImpl.class).slash(subscriptionResponseDto.getId())
                .withRel(DELETE_USER)
                .withType(HttpMethod.DELETE.name()));
        return links;
    }
}
