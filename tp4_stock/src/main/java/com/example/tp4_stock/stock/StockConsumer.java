package com.example.tp4_stock.stock;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);
    private final StockService stockService;
    private final ObjectMapper objectMapper;

    public StockConsumer(StockService stockService, ObjectMapper objectMapper) {
        this.stockService = stockService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "articles-topic", groupId = "stock-group")
    public void consume(ConsumerRecord<String, String> record) {
        LOGGER.info("Consumed message: {}", record.value());

        try {
            JsonNode articleNode = objectMapper.readTree(record.value());
            String nomArticle = articleNode.get("nomArticle").asText();
            int qteArticle = articleNode.get("qteArticle").asInt();

            // Update stock by subtracting the received quantity
            stockService.updateStock(nomArticle, qteArticle);

        } catch (Exception e) {
            LOGGER.error("Error processing Kafka message", e);
        }
    }
}
