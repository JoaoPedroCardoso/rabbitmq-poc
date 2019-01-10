package com.poc.rabbitmq.service;

import com.poc.rabbitmq.domain.Person;
import com.poc.rabbitmq.infrastruct.exception.ObjectNotFoundException;
import com.poc.rabbitmq.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Person with ID " + id + " was not found."));
    }

    public Person save(Person person) throws Exception {
        Person newPerson = personRepository.save(person);
        rabbitmqProducer.setMessage(newPerson.toString());
        rabbitmqProducer.run();
        return newPerson;
    }

}
