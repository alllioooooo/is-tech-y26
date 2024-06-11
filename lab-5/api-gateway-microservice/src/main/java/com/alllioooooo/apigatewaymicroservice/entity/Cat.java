package com.alllioooooo.apigatewaymicroservice.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cat {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private String color;
    private Long ownerId;
    private Set<Cat> friends = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Set<Cat> getFriends() {
        return friends;
    }

    public void setFriends(Set<Cat> friends) {
        this.friends = friends;
    }
}
