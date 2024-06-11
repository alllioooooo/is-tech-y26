package com.alllioooooo.ownermicroservice.repository;

import com.alllioooooo.ownermicroservice.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}