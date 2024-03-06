package com.raffaelbrandao.demo.dao;

import java.util.List;

import com.raffaelbrandao.demo.model.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class PersonDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void save(Person person) {
		em.persist(person);
	}

	public List<Person> findAll() {
		String jpql = "SELECT p FROM Person p";
		TypedQuery<Person> query = em.createQuery(jpql, Person.class);

		return query.getResultList();
	}

	public Person findById(Long personId) {
		return em.find(Person.class, personId);
	}

	@Transactional
	public Person update(Person person) {
		return em.merge(person);
	}

	@Transactional
	public void delete(Long personId) {
		Person person = em.find(Person.class, personId);
		em.remove(person);
	}
}
