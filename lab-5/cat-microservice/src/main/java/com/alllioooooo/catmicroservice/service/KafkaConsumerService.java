package com.alllioooooo.catmicroservice.service;

import com.alllioooooo.catmicroservice.entity.Cat;
import com.alllioooooo.catmicroservice.repository.CatRepository;
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
    private CatRepository catRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "request-cats", groupId = "cat-service-group")
    public void listenRequestAllCats(String message) {
        List<Cat> cats = catRepository.findAll();
        try {
            String catsJson = objectMapper.writeValueAsString(cats);
            kafkaTemplate.send("response-cats", catsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
