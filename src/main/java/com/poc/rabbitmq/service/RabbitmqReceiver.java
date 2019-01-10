package com.poc.rabbitmq.service;

import com.poc.rabbitmq.domain.MessageStorage;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Log
public class RabbitmqReceiver {

    @Autowired
    private MessageStorage storage;

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Received message from rabbitmq <" + message + ">");
        storage.put(message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
