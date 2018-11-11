package caro.db;

import caro.domain.Person;
import caro.domain.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class PersonRepositoryStub implements PersonRepository {
    
    private Map<String, Person> persons = new HashMap<>();
    private static PersonRepositoryStub INSTANCE;

    private PersonRepositoryStub() {
        Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris", Role.BIB, new ArrayList<>());
        add(administrator);
        Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens", Role.LID, new ArrayList<>(asList("an@ucll.be", "jonas@ucll.be")));
        Person an = new Person("an@ucll.be", "t", "An", "Cornelissen", Role.LID, new ArrayList<>(singletonList("jan@ucll.be")));
        Person jonas = new Person("jonas@ucll.be", "j", "Jonas", "Verschueren", Role.LID, new ArrayList<>(singletonList("jan@ucll.be")));
        add(jan);
        add(an);
        add(jonas);
    }

    public static PersonRepositoryStub getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersonRepositoryStub();
        }
        return INSTANCE;
    }

    public Person get(String personId) {
        if (personId == null) {
            throw new IllegalArgumentException("No id given");
        }
        return persons.get(personId);
    }

    public Person getAuthenticatedUser(String email, String password) {
        Person person = get(email);

        if (person != null && person.isCorrectPassword(password)) {
            return person;
        } else {
            return null;
        }
    }

    public List<Person> getAll() {
        return new ArrayList<>(persons.values());
    }

    public void add(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("No person given");
        }
        if (persons.containsKey(person.getUserId())) {
            throw new IllegalArgumentException("User already exists");
        }
        persons.put(person.getUserId(), person);
    }

    public void update(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("No person given");
        }
        persons.put(person.getUserId(), person);
    }

    public void delete(String personId) {
        if (personId == null) {
            throw new IllegalArgumentException("No id given");
        }
        persons.remove(personId);
    }

}
