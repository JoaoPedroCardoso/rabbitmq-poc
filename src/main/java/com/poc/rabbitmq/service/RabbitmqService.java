package com.poc.rabbitmq.service;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log
public class RabbitmqService {

    @Value("${spring.rabbitmq.topic.example.name}")
    public String topicExchangeName;

    @Value("${spring.rabbitmq.template.routing-key.example}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String data) {
        log.info("Sending message to topic " + topicExchangeName + "...");
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, data);
    }

}
