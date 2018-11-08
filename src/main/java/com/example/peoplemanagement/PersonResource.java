package com.example.peoplemanagement;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PersonResource {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person")
    public List<Person> get() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{personId}")
    public Person get(@PathVariable long personId) throws Exception {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new Exception("Person not found");
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Object> post(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{personId}").buildAndExpand(savedPerson.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/person/{personId}")
    public ResponseEntity<Object> put(@RequestBody Person person, @PathVariable long personId) {

        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            person.setId(personId);
            personRepository.save(person);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
