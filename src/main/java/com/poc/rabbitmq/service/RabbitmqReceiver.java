package com.poc.rabbitmq.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Log
public class RabbitmqReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        log.info("Received message from rabbitmq <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
