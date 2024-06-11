package com.alllioooooo.apigatewaymicroservice.entity;

import java.util.HashSet;
import java.util.Set;

public class Owner {

    private Long id;
    private String name;
    private String surname;
    private Set<Long> catIds = new HashSet<>();

    public Owner() {
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Long> getCatIds() {
        return catIds;
    }

    public void setCatIds(Set<Long> catIds) {
        this.catIds = catIds;
    }
}
