package com.alllioooooo.apigatewaymicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendCatRequest(String requestMessage) {
        kafkaTemplate.send("request-cats", requestMessage);
    }

    public void sendOwnerRequest(String requestMessage) {
        kafkaTemplate.send("request-owners", requestMessage);
    }
}
