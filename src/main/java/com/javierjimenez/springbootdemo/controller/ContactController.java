package com.javierjimenez.springbootdemo.controller;

import java.util.*;

import javax.validation.Valid;

import com.javierjimenez.springbootdemo.entity.Contact;
import com.javierjimenez.springbootdemo.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> all(){
        return contactService.findAll();
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact (@Valid @RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.save(contact));
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateMember(@Valid @RequestBody Contact contact,
      @PathVariable(value= "id") Long id) {
          return ResponseEntity.ok(contactService.updateContact(contact, id));
      } 
  
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Map<String,String>> deleteMemeber(@PathVariable Long id) {
      Map<String,String> response = new HashMap<>();
      if(contactService.delete(id)) {
        response.put("status", "success");
        response.put("message", "member deleted successfully");
         return ResponseEntity.ok(response);
      } else {
        response.put("status", "error");
        response.put("message", "Somthing went wrong when delete the member");
        return ResponseEntity.status(500).body(response);
      }
    }
    
}
