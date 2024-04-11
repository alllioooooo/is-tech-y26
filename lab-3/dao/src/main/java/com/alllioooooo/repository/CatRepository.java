package com.alllioooooo.repository;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.entity.Owner;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
