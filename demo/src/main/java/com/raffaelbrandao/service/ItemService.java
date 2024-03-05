package com.raffaelbrandao.service;

import java.util.List;

import com.github.javafaker.Faker;
import com.raffaelbrandao.model.Item;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Named
@ApplicationScoped
public class ItemService {
	List<Item> items;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		Query query = em.createQuery("SELECT i FROM Item i");
		items = query.getResultList();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item generateRandomItem() {
		Faker faker = new Faker();

		Item item = new Item();
		item.setCategory(faker.commerce().department());
		item.setName(faker.commerce().productName());
		item.setPrice((Double.valueOf(faker.commerce().price(10, 1000))));
		return item;
	}

	@Transactional
	public void update(Item item) {
		Integer id = item.getId();
		Item itemdb = em.find(Item.class, id);
		itemdb.setCategory(item.getCategory());
		itemdb.setName(item.getName());
		itemdb.setPrice(item.getPrice());
		em.persist(itemdb);

	}

	@Transactional
	public void save(Item item) {

		em.persist(item);

	}

}