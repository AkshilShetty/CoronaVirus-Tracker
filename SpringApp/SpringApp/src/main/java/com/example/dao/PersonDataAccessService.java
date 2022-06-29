package com.example.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
	
	private final JdbcTemplate jdbctemplate;
	

	@Autowired
	public PersonDataAccessService(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		final String sql = "SELECT id,name FROM person";
		List<Person> people = jdbctemplate.query(sql,(resultSet,i) -> {
			return new Person(
					UUID.fromString(resultSet.getString("id")),resultSet.getString("name"));
		});
		return people;
		//eturn List.of(new Person(UUID.randomUUID(),"FROM POSTGRES DB"));
	}

	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "SELECT id,name FROM person WHERE id = ?";
		
		return null;
	}

}
