package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Phone;

public class PhoneDTO {
    private long id;
    private String number;
    private String description;

    public PhoneDTO(String number, String description) {
        this.number = number;
        this.description = description;
    }
    public PhoneDTO(Phone phone) {
        if(phone.getId() != null)
            this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public static List<PhoneDTO> getDtos(List<Phone> phone){
        List<PhoneDTO> phoneDTO = new ArrayList<PhoneDTO>();
        phone.forEach(_phone->phoneDTO.add(new PhoneDTO(_phone)));
        return phoneDTO;
    }
}
