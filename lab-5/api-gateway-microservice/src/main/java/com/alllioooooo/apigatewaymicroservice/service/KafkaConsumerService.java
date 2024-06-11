package com.alllioooooo.apigatewaymicroservice.service;

import com.alllioooooo.apigatewaymicroservice.entity.Cat;
import com.alllioooooo.apigatewaymicroservice.entity.Owner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private CompletableFuture<List<Cat>> futureCats;
    private CompletableFuture<List<Owner>> futureOwners;

    public KafkaConsumerService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "response-cats", groupId = "api-gateway-group")
    public void listenResponseCats(String message) {
        try {
            List<Cat> cats = objectMapper.readValue(message, new TypeReference<List<Cat>>() {});
            if (futureCats != null) {
                futureCats.complete(cats);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "response-owners", groupId = "api-gateway-group")
    public void listenResponseOwners(String message) {
        try {
            List<Owner> owners = objectMapper.readValue(message, new TypeReference<List<Owner>>() {});
            if (futureOwners != null) {
                futureOwners.complete(owners);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public CompletableFuture<List<Cat>> getCats() {
        futureCats = new CompletableFuture<>();
        return futureCats;
    }

    public CompletableFuture<List<Owner>> getOwners() {
        futureOwners = new CompletableFuture<>();
        return futureOwners;
    }
}
