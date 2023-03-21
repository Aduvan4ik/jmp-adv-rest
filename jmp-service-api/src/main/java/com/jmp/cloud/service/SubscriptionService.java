package com.jmp.cloud.service;

import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface SubscriptionService {
    SubscriptionResponseDto getSubscriptionDto(Long id);

    List<SubscriptionResponseDto> getAllSubscriptionsDto();

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    SubscriptionResponseDto updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto);

    void deleteSubscription(Long id);
}
