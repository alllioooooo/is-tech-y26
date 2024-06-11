package com.alllioooooo.catmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCatUpdate(Long catId, String action) {
        String message = String.format("{\"catId\": \"%d\", \"action\": \"%s\"}", catId, action);
        kafkaTemplate.send("cat-updates", message);
    }
}
