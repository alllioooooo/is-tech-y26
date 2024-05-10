package com.alllioooooo.controllers;

import com.alllioooooo.entity.Owner;
import com.alllioooooo.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.findAllOwnersWithCats();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Owner owner = ownerService.findOwnerById(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.saveOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
