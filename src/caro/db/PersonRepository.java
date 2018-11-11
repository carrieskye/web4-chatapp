package caro.db;

import caro.domain.Person;

import java.util.List;

public interface PersonRepository {

    Person get(String userId);

    Person getAuthenticatedUser(String email, String password);

    List<Person> getAll();

    void add(Person person);

    void update(Person person);

    void delete(String userId);

}
