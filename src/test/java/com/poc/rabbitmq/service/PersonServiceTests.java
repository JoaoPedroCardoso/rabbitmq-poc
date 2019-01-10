package com.poc.rabbitmq.service;

import com.poc.rabbitmq.RabbitmqApplicationTests;
import com.poc.rabbitmq.domain.Person;
import com.poc.rabbitmq.infrastruct.exception.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTests extends RabbitmqApplicationTests {

    @Autowired
    private PersonService personService;

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @Test
    @DisplayName("should find a not empty list with all person in db")
    public void findAllPersonTest() {
        createNewPerson();
        List<Person> list = personService.findAll();
        assertTrue(!list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("should find a unique person by id")
    public void findPersonByIdTest() {
        createNewPerson();
        Person person = personService.findById(1L);
        assertEquals(Optional.of(1L), Optional.ofNullable(person.getId()));
    }

    @Test
    @DisplayName("shouldn't find a unique person by id")
    public void throwExceptionToFindPersonByIdTest() {
        assertThrows(ObjectNotFoundException.class, () -> personService.findById(2L));
    }

    @Test
    @DisplayName("should save a new person with success")
    public void saveNewPerson() {
        Person newPerson = new Person(null,"NewPerson");

        assertDoesNotThrow(() -> personService.save(newPerson));

        assertDoesNotThrow(() ->  rabbitmqProducer.run());

        List<Person> personList = personService.findAll();
        assertEquals("NewPerson", personList.get(personList.size() - 1).getName());
    }

    private void createNewPerson() {
        Person person = new Person(null,"PersonTest");
        try {
            personService.save(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
