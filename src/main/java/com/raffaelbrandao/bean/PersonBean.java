package com.raffaelbrandao.bean;

import java.io.Serializable;
import java.util.List;

import com.raffaelbrandao.enums.Gender;
import com.raffaelbrandao.model.Person;
import com.raffaelbrandao.service.PersonService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class PersonBean implements Serializable {
	private static final long serialVersionUID = -7360206069474825862L;

	@Getter
	@Setter
	private List<Person> people;

	@Setter
	private Person person;

	@Inject
	private PersonService service;

	@PostConstruct
	public void init() {

		setPeople(findAll());
	}

	public void save() {
		service.save(getPerson());
		people.add(getPerson());
		setPerson(new Person());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "The record was created successfully."));
	}

	public Person findById(@NotNull Long id) {
		return service.findById(id);
	}

	public List<Person> findAll() {
		return service.findAll();
	}

	public void update(@NotNull Long id) {
		Person person = getPeople().stream().filter(p -> p.getId() == id).findFirst().orElseThrow();
		service.update(person);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "The registry has been updated successfully."));
	}

	public void delete(@NotNull Long id) {
		service.delete(id);
		people.removeIf(p -> p.getId() == id);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "The record was successfully deleted."));
	}

	public Person getPerson() {
		if (this.person == null) {
			this.person = new Person();
		}
		return this.person;
	}

	public Gender[] genders() {
		return Gender.values();
	}
}
