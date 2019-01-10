package com.poc.rabbitmq.service;

import com.poc.rabbitmq.RabbitmqApplicationTests;
import com.poc.rabbitmq.domain.Person;
import com.poc.rabbitmq.infrastruct.exception.ObjectNotFoundException;
import com.poc.rabbitmq.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceTests extends RabbitmqApplicationTests {

    @InjectMocks
    private PersonService personService;

    @Mock
    private RabbitmqProducer rabbitmqProducer;

    @Mock
    private PersonRepository personRepository;

    @BeforeAll
    public static void BeforeClass(){
        MockitoAnnotations.initMocks(PersonServiceTests.class);
        mock(PersonService.class);
    }
    @Test
    @DisplayName("should find a not empty list with all person in db")
    public void findAllPersonTest() {
        when(personRepository.findAll()).thenReturn(
                Collections.singletonList(Person.of(1L, "test")));
        List<Person> list = personService.findAll();
        assertTrue(!list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("should find a unique person by id")
    public void findPersonByIdTest() {
        when(personRepository.findById(1L)).thenReturn(
                Optional.of(Person.of(1L, "test")));
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
        Person newPerson = Person.of(null,"NewPerson");
        when(personRepository.save(newPerson)).thenReturn(Person.of(1L,"NewPerson"));
        when(personRepository.findAll()).thenReturn(
                Collections.singletonList(Person.of(1L, "NewPerson")));

        assertDoesNotThrow(() -> personService.save(newPerson));

        assertDoesNotThrow(() ->  rabbitmqProducer.run());

        List<Person> personList = personService.findAll();
        assertEquals("NewPerson", personList.get(personList.size() - 1).getName());
    }

}
