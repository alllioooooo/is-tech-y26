package com.alllioooooo.controllers;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Cat>> getCatsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        List<Cat> cats;
        if (isAdmin) {
            cats = catService.findAllCats();
        } else {
            Long ownerId = catService.findOwnerIdByUsername(username);
            cats = catService.findCatsByOwnerId(ownerId);
        }

        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @catService.isOwner(#id, principal.username)")
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {
        Cat cat = catService.findCatById(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @GetMapping("/{id}/friends")
    @PreAuthorize("hasRole('ADMIN') or @catService.isOwner(#id, principal.username)")
    public ResponseEntity<Set<Cat>> getCatFriends(@PathVariable Long id) {
        Set<Cat> friends = catService.findCatFriends(id);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat, @RequestParam(required = false) Long ownerId) {
        Cat savedCat = catService.saveCat(cat, ownerId);
        return new ResponseEntity<>(savedCat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @catService.isOwner(#id, principal.username)")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catService.deleteCatById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}