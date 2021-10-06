package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import interfaces.IFacade;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import utils.EMF_Creator;

public class PersonFacade implements IFacade<PersonDTO> {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    public PersonDTO create(PersonDTO hobbyDTO) {
        Person person = new Person(hobbyDTO.getEmail(), hobbyDTO.getFirstName(), hobbyDTO.getLastName());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public PersonDTO getById(long id) {
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }

    public long getCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long personCount = (long) em.createNamedQuery("Person.getCount").getSingleResult();
            return personCount;
        } finally {
            em.close();
        }
    }

    public void addPhone(long id, Phone phone) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.addPhone(phone);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removePhone(long id, Phone phone) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.removePhone(phone);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void addHobby(long id, Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.addHobby(hobby);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removeHobby(long id, Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.removeHobby(hobby);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void setAddress(long id, Address address) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.setAddress(address);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removeAddress(long id, Address address) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        person.removeAddress(address);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Hobby getHobbyByName(String string) {
        return null;
    }

    public List<PersonDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createNamedQuery("Person.getAll", Person.class);
        List<Person> allPersons = query.getResultList();
        return PersonDTO.getDtos(allPersons);
    }

    @Override
    public List<PersonDTO> getSpecific(String valueType, String value) {
        EntityManager em = emf.createEntityManager();

        if (valueType.equals("FirstName")) {
            TypedQuery<Person> query = em.createNamedQuery("Person.getPerson", Person.class);
            query.setParameter("firstName", value);
            List<Person> persons = query.getResultList();
            return PersonDTO.getDtos(persons);
        } else if (valueType.equals("Phone")) {
            TypedQuery<Person> query = em.createNamedQuery("Person.getByPhone", Person.class);
            query.setParameter("number", "22222222");
            System.out.println(query.getResultList().size());
            Person person = query.getSingleResult();
            return PersonDTO.getDtos(Collections.singletonList(person));
        } else {
        }


        return null;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade PersonFacade = getInstance(emf);
        PersonFacade.getAll().forEach(dto -> System.out.println(dto));
    }
}
