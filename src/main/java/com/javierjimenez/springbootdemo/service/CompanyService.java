package com.javierjimenez.springbootdemo.service;

import java.util.List;

import com.javierjimenez.springbootdemo.entity.Company;
import com.javierjimenez.springbootdemo.repository.CompanyRepository;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private static final Logger LOGGER = Logger.getLogger(CompanyService.class.getName());
  private CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public List<Company> findAll() {
    return companyRepository.findAll();
  }

  public Company findCompanyByName(String name) {
    return companyRepository.findByName(name);
}

  public Company save(Company company) {
    if (company == null) { 
			LOGGER.log(Level.SEVERE,
					"Company is null. Are you sure you have connected your form to the application?");
			return null;
		}
    return companyRepository.save(company);
  }

  @Nullable
  public Company updateCompany(Company company, Long id) {
    Company toUpdateCompany = companyRepository.findById(id).orElse(null);
    if(toUpdateCompany != null) {
      toUpdateCompany.setName(company.getName());
    }
    return companyRepository.save(toUpdateCompany);
  }

  public Boolean delete (Long id) {
    Company toDelete  = companyRepository.findById(id).orElse(null);
    if(toDelete != null) {
        companyRepository.delete(toDelete);
        return true;
    }
    return false;
  }

}