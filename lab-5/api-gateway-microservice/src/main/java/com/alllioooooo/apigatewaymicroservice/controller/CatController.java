package com.alllioooooo.apigatewaymicroservice.controller;

import com.alllioooooo.apigatewaymicroservice.entity.Cat;
import com.alllioooooo.apigatewaymicroservice.service.KafkaConsumerService;
import com.alllioooooo.apigatewaymicroservice.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Cat>> getAllCats() {
        kafkaProducerService.sendCatRequest("Fetch all cats");

        try {
            List<Cat> cats = kafkaConsumerService.getCats().get();
            return new ResponseEntity<>(cats, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
