package facades;

import dtos.PersonDTO;
import entities.Person;
import interfaces.IFacade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class PersonFacade implements IFacade <PersonDTO>{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    private PersonFacade() {}
    
    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PersonDTO create(PersonDTO hobbyDTO){
        Person hobby = new Person(hobbyDTO.getEmail(), hobbyDTO.getFirstName(), hobbyDTO.getLastName());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(hobby);
    }

    public PersonDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }
    
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createNamedQuery("Person.getCount").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createNamedQuery("Person.getAll", Person.class);
        List<Person> allPersons = query.getResultList();
        return PersonDTO.getDtos(allPersons);
    }

    @Override
    public List<PersonDTO> getSpecific(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createNamedQuery("Person.getPerson", Person.class);
        query.setParameter("firstName", name);
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade PersonFacade = getInstance(emf);
        PersonFacade.getAll().forEach(dto->System.out.println(dto));
    }
}
