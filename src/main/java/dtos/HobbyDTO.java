package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Hobby;

public class HobbyDTO {
    private long id;
    private String name;
    private String description;

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public HobbyDTO(Hobby hobby) {
        if(hobby.getId() != null)
            this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public static List<HobbyDTO> getDtos(List<Hobby> hobby){
        List<HobbyDTO> hobbyDTO = new ArrayList<HobbyDTO>();
        hobby.forEach(_hobby->hobbyDTO.add(new HobbyDTO(_hobby)));
        return hobbyDTO;
    }
    
}
