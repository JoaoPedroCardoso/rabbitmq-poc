package com.poc.rabbitmq.controller;

import com.poc.rabbitmq.controller.respone.MessageResponse;
import com.poc.rabbitmq.domain.MessageStorage;
import com.poc.rabbitmq.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rabbitmq")
public class RabbitmqController {

    @Autowired
    private RabbitmqService producer;

    @Autowired
    private MessageStorage storage;

    @RequestMapping(value="/producer", method = RequestMethod.GET)
    public ResponseEntity<Void> producer(@RequestParam("data")String data){
        producer.send(data);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/consumer", method = RequestMethod.GET)
    public ResponseEntity<MessageResponse> getAllRecievedMessage(){
        String messages = storage.toString();
        storage.clear();

        return ResponseEntity.ok().body(new MessageResponse(messages));
    }

}
