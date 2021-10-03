package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Address;

public class AddressDTO {
    private long id;
    private String street;
    private String additionalInfo;

    public AddressDTO(String street, String additionalInfo){
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public AddressDTO(Address address) {
        if(address.getId() != null)
            this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
    }

    public static List<AddressDTO> getDtos(List<Address> address) {
        List<AddressDTO> addressDTO = new ArrayList<AddressDTO>();
        address.forEach(_address -> addressDTO.add(new AddressDTO(_address)));
        return addressDTO;
    }

    public long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}
