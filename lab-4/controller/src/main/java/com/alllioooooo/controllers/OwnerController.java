package com.alllioooooo.controllers;

import com.alllioooooo.entity.Owner;
import com.alllioooooo.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.findAllOwnersWithCats();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @ownerService.isOwner(#id, principal.username)")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findOwnerById(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.saveOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @ownerService.isOwner(#id, principal.username)")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}