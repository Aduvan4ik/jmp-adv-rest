package com.jmp.dto;

import java.util.Objects;

public record UserRequestDto(Long id, String name, String surname, String birthday) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserRequestDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.birthday, that.birthday);
    }

    @Override
    public String toString() {
        return "UserRequestDto[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "birthday=" + birthday + ']';
    }

}
