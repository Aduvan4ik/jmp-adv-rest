package com.jmp.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public final class UserResponseDto extends RepresentationModel<UserResponseDto> {
    private final Long id;
    private final String name;
    private final String surname;
    private final String birthday;

    public UserResponseDto(Long id,
                           String name,
                           String surname,
                           String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserResponseDto) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday);
    }

    @Override
    public String toString() {
        return "UserResponseDto[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "birthday=" + birthday + ']';
    }

}
