package com.jmp.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public final class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {
    private final Long id;
    private final Long userId;
    private final String startDate;

    public SubscriptionResponseDto(Long id,
                                   Long userId,
                                   String startDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SubscriptionResponseDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, startDate);
    }

    @Override
    public String toString() {
        return "SubscriptionResponseDto[" +
                "id=" + id + ", " +
                "userId=" + userId + ", " +
                "startDate=" + startDate + ']';
    }

}
