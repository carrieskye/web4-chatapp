package caro.domain;

import caro.db.PersonRepository;
import caro.db.PersonRepositoryStub;

import java.util.List;

public class PersonService {

    private PersonRepository personRepository = PersonRepositoryStub.getInstance();

    public PersonService() {

    }

    public Person getPerson(String personId) {
        return getPersonRepository().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonRepository().getAll();
    }

    public void addPerson(Person person) {
        getPersonRepository().add(person);
    }

    public void updatePersons(Person person) {
        getPersonRepository().update(person);
    }

    public void deletePerson(String id) {
        getPersonRepository().delete(id);
    }

    public Person getAuthenticatedUser(String email, String password) {
        return getPersonRepository().getAuthenticatedUser(email, password);
    }

    private PersonRepository getPersonRepository() {
        return personRepository;
    }

}
