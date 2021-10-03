package facades;

import dtos.CityInfoDTO;
import entities.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class CityInfoFacade {

    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;
    
    private CityInfoFacade() {}
    
    public static CityInfoFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CityInfoDTO create(CityInfoDTO cityInfoDTO){
        CityInfo cityInfo = new CityInfo(cityInfoDTO.getZipCode(), cityInfoDTO.getCity());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CityInfoDTO(cityInfo);
    }

    public CityInfoDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new CityInfoDTO(em.find(CityInfo.class, id));
    }
    
    public long getCityInfoCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long cityInfoCount = (long)em.createQuery("CityInfo.getCount").getSingleResult();
            return cityInfoCount;
        }finally{  
            em.close();
        }
    }
    
    public List<CityInfoDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<CityInfo> query = em.createQuery("CityInfo.getAll", CityInfo.class);
        List<CityInfo> allCityInfo = query.getResultList();
        return CityInfoDTO.getDtos(allCityInfo);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        CityInfoFacade cityInfoFacade = getInstance(emf);
        cityInfoFacade.getAll().forEach(dto->System.out.println(dto));
    }

}
