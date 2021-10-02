package dtos;

import java.util.List;

import entities.Address;

public class CityInfoDTO {
    private long id;
    private String zipCode;
    private String city;
    private List<Address> addresses;
    
    public CityInfoDTO(long id, String zipCode, String city, List<Address> addresses) {
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
        this.addresses = addresses;
    }
}
