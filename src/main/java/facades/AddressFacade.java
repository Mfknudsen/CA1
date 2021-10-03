package facades;

import dtos.AddressDTO;
import entities.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;
    
    private AddressFacade() {}
    
    public static AddressFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AddressDTO create(AddressDTO addressDTO){
        Address address = new Address(addressDTO.getStreet(), addressDTO.getAdditionalInfo());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }

    public AddressDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new AddressDTO(em.find(Address.class, id));
    }
    
    public long getAddressCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long addressCount = (long)em.createNamedQuery("Address.getCount").getSingleResult();
            return addressCount;
        }finally{  
            em.close();
        }
    }
    
    public List<AddressDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Address> query = em.createNamedQuery("Address.getAll", Address.class);
        List<Address> allAddresses = query.getResultList();
        return AddressDTO.getDtos(allAddresses);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        AddressFacade addressFacade = getInstance(emf);
        addressFacade.getAll().forEach(dto->System.out.println(dto));
    }

    public List<AddressDTO> getByStreet(String street) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Address> query = em.createNamedQuery("Address.getByStreet", Address.class);
        query.setParameter("street", street);
        List<Address> addresses = query.getResultList();
        return AddressDTO.getDtos(addresses);
    }

}
