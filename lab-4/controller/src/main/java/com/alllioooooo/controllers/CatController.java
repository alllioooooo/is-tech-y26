package com.alllioooooo.controllers;

import com.alllioooooo.entity.Cat;
import com.alllioooooo.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cats")
public class CatController {

    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        List<Cat> cats = catService.findAllCats();
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {
        Cat cat = catService.findCatById(id);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<Set<Cat>> getCatFriends(@PathVariable Long id) {
        Set<Cat> friends = catService.findCatFriends(id);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat, @RequestParam(required = false) Long ownerId) {
        Cat savedCat = catService.saveCat(cat, ownerId);
        return new ResponseEntity<>(savedCat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catService.deleteCatById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
