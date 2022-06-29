package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

	private static List<Person> DB = new ArrayList<>();
	@Override
	public int insertPerson(UUID id, Person person) {
		// TODO Auto-generated method stub
		DB.add(new Person(id,person.getName()));
		return 1;
	}
	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		return DB;
	}
	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		Optional<Person> maybe = selectPersonById(id);
		if(maybe.isEmpty()) {
			return 0;
		}
		DB.remove(maybe.get());
		return 1;
	}
	@Override
	public int updatePersonById(UUID id, Person person) {
		// TODO Auto-generated method stub
		return selectPersonById(id).map(p -> {
			int indexOfPersonToDelet = DB.indexOf(p);
			if(indexOfPersonToDelet >= 0) {
				DB.set(indexOfPersonToDelet, new Person(id,person.getName()));
				return 1;
			}
			return 0;
		}).orElse(0);
	}
	@Override
	public Optional<Person> selectPersonById(UUID id) {
		// TODO Auto-generated method stub
		return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
	}

	
}
