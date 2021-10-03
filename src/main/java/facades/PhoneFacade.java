package facades;

import dtos.PhoneDTO;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class PhoneFacade {

    private static PhoneFacade instance;
    private static EntityManagerFactory emf;
    
    private PhoneFacade() {}
    
    public static PhoneFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PhoneFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PhoneDTO create(PhoneDTO hobbyDTO){
        Phone hobby = new Phone(hobbyDTO.getNumber(), hobbyDTO.getDescription());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PhoneDTO(hobby);
    }

    public PhoneDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new PhoneDTO(em.find(Phone.class, id));
    }
    
    public long getPhoneCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long phoneCount = (long)em.createQuery("Phone.getCount").getSingleResult();
            return phoneCount;
        }finally{  
            em.close();
        }
    }
    
    public List<PhoneDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Phone> query = em.createQuery("Phone.getAll", Phone.class);
        List<Phone> allPhones = query.getResultList();
        return PhoneDTO.getDtos(allPhones);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PhoneFacade PhoneFacade = getInstance(emf);
        PhoneFacade.getAll().forEach(dto->System.out.println(dto));
    }

}
