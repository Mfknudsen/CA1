package facades;

import dtos.HobbyDTO;
import entities.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import interfaces.IFacade;
import utils.EMF_Creator;

public class HobbyFacade implements IFacade<HobbyDTO> {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;
    
    private HobbyFacade() {}
    
    public static HobbyFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HobbyDTO create(HobbyDTO hobbyDTO){
        Hobby hobby = new Hobby(hobbyDTO.getName(), hobbyDTO.getWikiLink(), hobbyDTO.getType(), hobbyDTO.getCategory());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }

    public HobbyDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new HobbyDTO(em.find(Hobby.class, id));
    }
    
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long hobbyCount = (long)em.createNamedQuery("Hobby.getCount").getSingleResult();
            return hobbyCount;
        }finally{  
            em.close();
        }
    }
    
    public List<HobbyDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hobby> query = em.createNamedQuery("Hobby.getAll", Hobby.class);
        List<Hobby> allHobby = query.getResultList();
        return HobbyDTO.getDtos(allHobby);
    }

    @Override
    public List<HobbyDTO> getSpecific(String one, String two) {
        return null;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        HobbyFacade hobbyFacade = getInstance(emf);
        hobbyFacade.getAll().forEach(dto->System.out.println(dto));
    }

}
