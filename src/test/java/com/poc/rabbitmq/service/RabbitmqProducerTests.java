package com.poc.rabbitmq.service;

import com.poc.rabbitmq.RabbitmqApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RabbitmqProducerTests extends RabbitmqApplicationTests {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @Test
    @DisplayName("should send a message to topic without exception")
    public void sendMessageTest() {
        assertDoesNotThrow(() -> rabbitmqProducer.run());
    }

    @Test
    @DisplayName("should set a message to rabbitmqProducer without exception")
    public void setMessageTest() {
        assertDoesNotThrow(() -> rabbitmqProducer.setMessage("test"));
        rabbitmqProducer.setMessage("test");
        assertEquals("test", rabbitmqProducer.getMessage());
    }

}
