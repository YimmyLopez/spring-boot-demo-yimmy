package com.javierjimenez.springbootdemo.service;

import com.javierjimenez.springbootdemo.entity.*;
import com.javierjimenez.springbootdemo.repository.*;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

@Service 
public class ContactService {
	private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
	private ContactRepository contactRepository;
	private CompanyRepository companyRepository;

	public ContactService(ContactRepository contactRepository,CompanyRepository companyRepository) { 
		this.contactRepository = contactRepository;
		this.companyRepository = companyRepository;
	}

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	public long count() {
		return contactRepository.count();
	}

	public Boolean delete (Long id) {
		Company toDelete  = companyRepository.findById(id).orElse(null);
		if(toDelete != null) {
			companyRepository.delete(toDelete);
			return true;
		}
		return false;
	 }

	public Contact save(Contact contact) {
		if (contact == null) { 
			LOGGER.log(Level.SEVERE,
					"Contact is null. Are you sure you have connected your form to the application?");
			return null;
		}
		return contactRepository.save(contact);
	}
	
	@Nullable
	public Contact updateContact(Contact contact, Long id) {
	  Contact toUpdateContact = contactRepository.findById(id).orElse(null);
	  if(toUpdateContact != null) {
		toUpdateContact.setFirstName(contact.getFirstName());
		toUpdateContact.setLastName(contact.getLastName());
		toUpdateContact.setEmail(contact.getEmail());
		toUpdateContact.setCompany(contact.getCompany());
		toUpdateContact.setStatus(contact.getStatus());
	  }
	  return contactRepository.save(toUpdateContact);
	}   

	//#region Seeder
    @PostConstruct 
	public void populateTestData() 
	{
		if (companyRepository.count() == 0) {
			companyRepository.saveAll( 
				Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
					.map(Company::new)
					.collect(Collectors.toList()));
		}

		if (contactRepository.count() == 0) {
			Random r = new Random(0);
			List<Company> companies = companyRepository.findAll();
			contactRepository.saveAll( 
				Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
					"Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
					"Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
					"Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
					"Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
					"Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
					"Jaydan Jackson", "Bernard Nilsen")
					.map(name -> {
						String[] split = name.split(" ");
						Contact contact = new Contact();
						contact.setFirstName(split[0]);
						contact.setLastName(split[1]);
						contact.setCompany(companies.get(r.nextInt(companies.size())));
						contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
						String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();
						contact.setEmail(email);
						return contact;
					}).collect(Collectors.toList()));
		}	
	}
	//#endregion Seeder
}
