package db;

import domain.Person;

import java.util.List;

public interface PersonRepository {

    void add(Person person);

    void delete(String userId);

    Person get(String userId);

    List<Person> getAll();

    Person getAuthenticatedUser(String email, String password);

    void update(Person person);

}