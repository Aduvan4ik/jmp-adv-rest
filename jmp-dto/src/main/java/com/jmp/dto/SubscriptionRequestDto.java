package com.jmp.dto;

import java.util.Objects;

public record SubscriptionRequestDto(Long id, Long userId) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SubscriptionRequestDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.userId, that.userId);
    }

    @Override
    public String toString() {
        return "SubscriptionRequestDto[" +
                "id=" + id + ", " +
                "userId=" + userId + ']';
    }

}
