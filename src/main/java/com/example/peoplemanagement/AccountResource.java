package com.example.peoplemanagement;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ResourceNotFoundException;

@RestController
public class AccountResource {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person/{personId}/account")
    public Page<Account> getAllAccountsByUserId(@PathVariable Long personId, Pageable pageable) {
        return accountRepository.findByPersonId(personId, pageable);
    }

    @PostMapping("/person/{id}/account")
    public ResponseEntity<Object> post(@PathVariable Long id, @RequestBody Account account) {

        Optional<Account> savedAccount = personRepository.findById(id).map(person -> {
            account.setPerson(person);
            return accountRepository.save(account);
        });

        if(savedAccount.isPresent()){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(savedAccount.get().getAccountNumber()).toUri();
            return ResponseEntity.created(location).build();
        }else{
            throw new ResourceNotFoundException("User Id " + id + " not found");
        }
    }
}
