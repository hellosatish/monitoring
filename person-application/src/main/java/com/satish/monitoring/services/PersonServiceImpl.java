package com.satish.monitoring.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.satish.monitoring.db.entities.PersonEntity;
import com.satish.monitoring.db.repositories.PersonRepository;
import com.satish.monitoring.web.models.Person;

/**
 * 
 * @author Satish Sharma
 * 
 * <pre>
 *  Implementation class for {@link PersonService}
 * </pre>
 */
@Component
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepo;

	@Autowired
	PersonServiceImpl(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	/**
	 * Convert {@link Person} Object to {@link PersonEntity} object Set the
	 * personId if present else return object with id null/0
	 */
	private final Function<Person, PersonEntity> personToEntity = new Function<Person, PersonEntity>() {
		@Override
		public PersonEntity apply(Person person) {
			if (person.getPersonId() == 0) {
				return new PersonEntity(person.getFirstName(), person.getLastName(), person.getEmail());
			} else {
				return new PersonEntity(person.getPersonId(), person.getFirstName(), person.getLastName(),
						person.getEmail());
			}
		}
	};

	/**
	 * Convert {@link PersonEntity} to {@link Person} object
	 */
	private final Function<PersonEntity, Person> entityToPerson = new Function<PersonEntity, Person>() {
		@Override
		public Person apply(PersonEntity entity) {
			return new Person(entity.getPersonId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
		}
	};

	
	/**
	 * If record is present then convert the record else return the empty {@link Optional}
	 */
	@Override
	public Optional<Person> getPersonById(int personId) {
		return  personRepo.findById(personId).map(s ->  entityToPerson.apply(s));
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepo.findAll().parallelStream()
				.map(s ->  entityToPerson.apply(s))
				.collect(Collectors.toList());
	}

	@Override
	public boolean removePerson(int personId) {
		personRepo.deleteById(personId);
		return true;
	}

	@Override
	public Optional<Person> saveUpdatePerson(Person person) {
		if(person.getPersonId() == 0 || personRepo.existsById(person.getPersonId())){
			PersonEntity entity = personRepo.save(personToEntity.apply(person));
			return Optional.of(entityToPerson.apply(entity));
		}else{
			return Optional.empty();
		}
	}

}
