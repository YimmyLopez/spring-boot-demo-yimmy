package com.javierjimenez.springbootdemo.repository;

import com.javierjimenez.springbootdemo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
}