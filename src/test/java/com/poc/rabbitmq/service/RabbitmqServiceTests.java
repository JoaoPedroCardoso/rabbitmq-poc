package com.poc.rabbitmq.service;

import com.poc.rabbitmq.RabbitmqApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RabbitmqServiceTests extends RabbitmqApplicationTests {

    @Autowired
    private RabbitmqService rabbitmqService;

    @Test
    @DisplayName("shouldn't throw exception when send message")
    public void DoesNotThrowExceptionWhenSendMessageTest() {
        assertDoesNotThrow(() -> rabbitmqService.send("teste"));
    }

}
