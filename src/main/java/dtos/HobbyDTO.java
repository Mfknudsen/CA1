package dtos;

import java.util.ArrayList;
import java.util.List;

import entities.Hobby;

public class HobbyDTO {
    private long id;
    private String name;
    private String wikiLink;
    private String category;
    private String type;

    public HobbyDTO(String name, String wikiLink, String category, String type) {
        this.name = name;
        this.wikiLink = wikiLink;
        this.category = category;
        this.type = type;
    }

    public HobbyDTO(Hobby hobby) {
        if(hobby.getId() != null)
            this.id = hobby.getId();
        this.name = hobby.getName();
        this.wikiLink = hobby.getWikiLink();
        this.category = hobby.getCategory();
        this.type = hobby.getType();
    }

    public static List<HobbyDTO> getDtos(List<Hobby> hobby){
        List<HobbyDTO> hobbyDTO = new ArrayList<HobbyDTO>();
        hobby.forEach(_hobby->hobbyDTO.add(new HobbyDTO(_hobby)));
        return hobbyDTO;
    }
    
}
