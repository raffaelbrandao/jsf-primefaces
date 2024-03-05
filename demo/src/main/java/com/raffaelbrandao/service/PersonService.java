package com.raffaelbrandao.service;

import java.util.List;

import com.raffaelbrandao.dao.PersonDAO;
import com.raffaelbrandao.model.Person;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class PersonService {
	@Inject
	private PersonDAO dao;

	public void save(Person person) {
		dao.save(person);
	}

	public List<Person> findAll() {
		return dao.findAll();
	}

	public Person findById(Long personId) {
		return dao.findById(personId);
	}

	public Person update(Person person) {
		return dao.update(person);
	}

	public void delete(Long personId) {
		dao.delete(personId);
	}

}
