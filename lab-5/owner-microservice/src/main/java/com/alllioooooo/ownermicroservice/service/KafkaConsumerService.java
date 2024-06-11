package com.alllioooooo.ownermicroservice.service;

import com.alllioooooo.ownermicroservice.entity.Owner;
import com.alllioooooo.ownermicroservice.repository.OwnerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "request-owners", groupId = "owner-service-group")
    public void listenRequestAllOwners(String message) {
        List<Owner> owners = ownerRepository.findAll();
        try {
            String ownersJson = objectMapper.writeValueAsString(owners);
            kafkaTemplate.send("response-owners", ownersJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
