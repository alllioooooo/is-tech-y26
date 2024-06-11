package com.alllioooooo.catmicroservice.repository;

import com.alllioooooo.catmicroservice.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByOwnerId(Long ownerId);
}