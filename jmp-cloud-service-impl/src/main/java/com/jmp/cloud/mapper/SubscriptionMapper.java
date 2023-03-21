package com.jmp.cloud.mapper;

import com.jmp.cloud.entity.Subscription;
import com.jmp.dto.SubscriptionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "userId", source = "user.id")
    SubscriptionResponseDto mapToDto(Subscription subscription);

    List<SubscriptionResponseDto> mapToDtos(List<Subscription> subscriptionList);

}
