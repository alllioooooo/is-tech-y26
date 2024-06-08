package com.alllioooooo.ownermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOwnerUpdate(Long ownerId, String action) {
        String message = String.format("{\"ownerId\": \"%d\", \"action\": \"%s\"}", ownerId, action);
        kafkaTemplate.send("owner-updates", message);
    }
}
