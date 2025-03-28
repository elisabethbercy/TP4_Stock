package com.example.tp4_stock.stock;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class StockConsumer {
 private final static Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);

    private final StockService stService;
    private final ObjectMapper objectMapper;

    public StockConsumer(StockService stService, ObjectMapper objectMapper) {
        this.stService = stService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
        topics = "articles-topic", 
        groupId = "stock-group")
    public void consume(ConsumerRecord<String, String> record) {

        LOGGER.info("Consumed message: {}", record.value());

        try {

            //article item qty and name from fromatted Json
            JsonNode articleNode = objectMapper.readTree(record.value());

            String nomArticle = articleNode.get("nomArticle").asText();
            int qteArticle = articleNode.get("qteArticle").asInt();

            // 
            stService.updateStock(nomArticle, qteArticle);

        } catch (Exception e) {
            LOGGER.error("Erreur lors de la consommation du message Kafka", e);
            System.err.println("Erreur lors de la consommation du message Kafka");
        }
    }
}
