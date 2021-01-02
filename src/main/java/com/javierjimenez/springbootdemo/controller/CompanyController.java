package com.javierjimenez.springbootdemo.controller;

import java.util.*;

import javax.validation.Valid;

import com.javierjimenez.springbootdemo.entity.Company;
import com.javierjimenez.springbootdemo.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    
    @GetMapping("/companies")
    public List<Company> all() {
        return companyService.findAll();
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createMember (@Valid @RequestBody Company company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateMember(@Valid @RequestBody Company company,
      @PathVariable(value= "id") Long id) {
          return ResponseEntity.ok(companyService.updateCompany(company, id));
      } 
  
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Map<String,String>> deleteMemeber(@PathVariable Long id) {
      Map<String,String> response = new HashMap<>();
      if(companyService.delete(id)) {
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
