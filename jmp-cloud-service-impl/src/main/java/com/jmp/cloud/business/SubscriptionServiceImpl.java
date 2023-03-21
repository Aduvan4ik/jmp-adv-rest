package com.jmp.cloud.business;

import com.jmp.cloud.entity.Subscription;
import com.jmp.cloud.entity.User;
import com.jmp.cloud.exception.EntityNotFoundException;
import com.jmp.cloud.mapper.SubscriptionMapper;
import com.jmp.cloud.repository.SubscriptionRepository;
import com.jmp.cloud.repository.UserRepository;
import com.jmp.cloud.service.SubscriptionService;
import com.jmp.dto.SubscriptionRequestDto;
import com.jmp.dto.SubscriptionResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;


    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   SubscriptionMapper subscriptionMapper,
                                   UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.userRepository = userRepository;
    }

    @Override
    public SubscriptionResponseDto getSubscriptionDto(Long id) {
        return subscriptionMapper.mapToDto(getSubscription(id));
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscriptionsDto() {
        return subscriptionMapper.mapToDtos(subscriptionRepository.findAll());
    }

    @Override
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        User user = getUser(subscriptionRequestDto.userId());

        return subscriptionMapper.mapToDto(subscriptionRepository.save(new Subscription(user)));
    }

    @Override
    public SubscriptionResponseDto updateSubscription(Long id, SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = getSubscription(id);
        User user = getUser(subscriptionRequestDto.userId());

        subscription.setUser(user);
        return subscriptionMapper.mapToDto(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    private Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription with next id is not found: " + id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with next id is not found: " + id));

    }
}
