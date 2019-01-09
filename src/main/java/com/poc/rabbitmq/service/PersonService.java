package com.poc.rabbitmq.service;

import com.poc.rabbitmq.domain.Person;
import com.poc.rabbitmq.infrastruct.exception.ObjectNotFoundException;
import com.poc.rabbitmq.repository.PersonRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Person with ID " + id + " was not found."));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

}
