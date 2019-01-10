package com.poc.rabbitmq.service;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log
public class RabbitmqProducer  implements CommandLineRunner {

    @Value("${spring.rabbitmq.topic.exchange.name}")
    public String topicExchangeName;

    @Value("${spring.rabbitmq.template.routing-key.person}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;
    private RabbitmqReceiver receiver;
    private String message = "";

    public RabbitmqProducer(RabbitmqReceiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Sending message to topic " + topicExchangeName + "...");
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
